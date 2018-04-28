package fr.jeu.main.render;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import fr.jeu.main.math.Vector3f;
public class Shader {
	public int program;
	
	public static final Shader MAIN = new Shader("/shader/main.vert", "/shader/main.frag");
	public Shader(String vertex , String fragment){
		program = glCreateProgram();
		
		if(program == GL_FALSE){
			System.err.println("Shader program Error !");
			System.exit(1);
		}
		
		createShader(loadShader(vertex), GL_VERTEX_SHADER);
		createShader(loadShader(fragment), GL_FRAGMENT_SHADER);
		glLinkProgram(program);
		glValidateProgram(program);
	}
	
	private void createShader(String source,int type){
		int shader = glCreateShader(type);
		if(shader == GL_FALSE){
			System.err.println("Shader Error  : " + shader);
			System.exit(1);
		}
		
		glShaderSource(shader, source);
		glCompileShader(shader);
		
		if(glGetShaderi(shader, GL_COMPILE_STATUS) == GL_FALSE){
			System.err.println(glGetShaderInfoLog(shader, 2048));
			System.exit(1);
		}
		
		glAttachShader(program, shader);
	}
	
	private String loadShader(String input){
		String r="";
		
		try {
			InputStream is = Shader.class.getResourceAsStream(input);
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			String buffer = "";
			while((buffer = reader.readLine())!=null){
				r+=buffer+"\n";
			}
			reader.close();
			return r;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void setUniform(String name, float v){
		glUniform1f(glGetUniformLocation(program, name), v);
	}
	
	public void setUniform(String name, Vector3f v){
		glUniform3f(glGetUniformLocation(program, name), v.getX(),v.getY(),v.getZ());
	}
	
	public void bind(){
		glUseProgram(program);
		
	}
}
