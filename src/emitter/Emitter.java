package emitter;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

import ast.ProcedureDeclaration;

/**
 * The Emitter class outputs text to an external file
 * It is used to generate the compiled MIPS code
 * @author Pranav Varmaraja
 * @version 01/11/2022
 */
public class Emitter
{
	private PrintWriter out;
	private int currId;
	private ProcedureDeclaration proc;
	private int excessStackHeight;

	/**
	 * creates an Emitter with a given filename
	 * @param outputFileName String file to be outputted to
	 */
	public Emitter(String outputFileName)
	{
		try
		{
			out = new PrintWriter(new FileWriter(outputFileName), true);
		}
		catch(IOException e)
		{
			throw new RuntimeException(e);
		}
		currId = 0;
		proc = null;
		excessStackHeight = 0;
	}

	/**
	 * prints a single line to the output file, with nonlabels (any line not ending with a colon) indented
	 * @param code String to be outputted to the file
	 */
	public void emit(String code)
	{
		if (!code.endsWith(":"))
			code = "\t" + code;
		out.println(code);
	}

	/**
	 * closes the file, should be called after all calls to emit()
	 */
	public void close()
	{
		out.close();
	}

	/**
	 * emits the code to add the contents of register reg to the stack pointer ($sp)
	 * @param reg String register to be pushed onto stack
	 */
	public void emitPush(String reg) {
		emit("subu $sp $sp 4");
		emit("sw " + reg + " " + "($sp)");
		excessStackHeight+=4;
	}

	/**
	 * emits the code to remove the contents of the top of the stack and move it into register erg
	 * @param reg register to load top of stack into
	 */
	public void emitPop(String reg) {
		emit("lw " + reg + " " + "($sp)");
		emit("addu $sp $sp 4");
		excessStackHeight-=4;

	}

	/**
	 * returns the nextlabel id #, e.g. startif1, startif2, so that labelnames are unique
	 * @return int the next label id, the previous id + 1
	 */
	public int nextLabelID() {
		currId += 1;
		return currId;
	}

	/**
	 * sets the procedurecontext of the currently compiled ProcedureDeclaration
	 * @param proc ProcedureDeclaration context to be set
	 */
	public void setProcedureContext(ProcedureDeclaration proc) {
		this.proc = proc;
		excessStackHeight = 0;
	}

	/**
	 * clears the procedurecontext by setting instance variable proc to null
	 */
	public void clearProcedureContext() {
		setProcedureContext(null);
	}

	/**
	 * gets the procedureContext
	 * @return ProcedureDeclaration currently compiled procedure declaration
	 */
	public ProcedureDeclaration getProcedureContext() {
		return proc;
	}

	/**
	 * checks if varName is a local variable
	 * @param varName variable name to be checked
	 * @return true if local, false otherwise
	 */
	public boolean isLocalVariable(String varName) {
		if(proc==null) {
			return false;
		}
		return proc.getParameters().contains(varName) || proc.getName().equals(varName) || proc.getVariables().contains(varName);

	}

	/**
	 * returns the stack pointer offset for a local variable
	 * @param varName variable name to be checked
	 * @return int stack pointer offset of the variable
	 */
	public int getOffset(String varName) {
		if(proc.getName().equals(varName)) {
			return excessStackHeight;
		}
		if(proc.getVariables().contains(varName)) {
			return excessStackHeight-4*(proc.getVariables().indexOf(varName)+1);
		}
		List<String> params = new ArrayList<String>(proc.getParameters());
		Collections.reverse(params);
		return params.indexOf(varName)*4 + excessStackHeight+4;
	}


}