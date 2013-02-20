/*
 * main.java
 * 
 * Copyright 2013 Zola Mahlaza <adeebnqo@gmail.com>
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 * 
 * 
 */
import java.io.Console;
import java.io.File;
import java.util.Vector;
import java.util.Scanner;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.io.OutputStream;
import java.io.InputStream;
import java.util.Scanner;
class main{
	static Console terminal;
	static String working_directory;
	public static void main(String[] args){
		String entered_cmd ="";
		String username = System.getProperty("user.name");
		working_directory = System.getProperty("user.dir");
		terminal = System.console();
		if (terminal!=null){
			//Some cool art when you log in
			System.out.println("       _..--'''@   \033[31m@\033[0m'''--.._");
			System.out.println("     .'   \033[31m@\033[0m_/-//-\\/>/>'/ \033[31m@\033[0m  '.");
			System.out.println("    (  \033[31m@\033[0m  /_<//<'/----------^-)");
   			System.out.println("    |'._  \033[31m@\033[0m     //|###########|");
			System.out.println("    |~  ''--..@|',|}}}}}}}}}}}|");
			System.out.println("    |  ~   ~   |/ |###########|");
 			System.out.println("    | ~~  ~   ~|./|{{{{{{{{{{{|");
			System.out.println("     '._ ~ ~ ~ |,/`````````````");
			System.out.println("        ''--.~.|/");
			System.out.println("Welcome to JBa$h");
			do{
				System.out.print(username+"@JBa$h:#");
				entered_cmd = terminal.readLine();
				if (!entered_cmd.equals("exit")){
					//if cmd does not contain pipes or "and(&)"
					if ((!entered_cmd.contains("|")) &&(!entered_cmd.contains("&"))){
						String[] whole_entered_cmd = entered_cmd.split(" ");
						switch(whole_entered_cmd[0]){
							case "clear":
								run("clear");
								break;
							case "ls":
								boolean option_l = (whole_entered_cmd.length==3 ? true:false);
								if (whole_entered_cmd.length==2){
									run("ls "+whole_entered_cmd[1]);
								}
								else if (whole_entered_cmd.length==1){
									run("ls "+working_directory);
								}
								break;
							case "echo":
								if (whole_entered_cmd.length==1){
									run("echo ");
								}
								else{
									String print_string = entered_cmd.replace("echo ","");
									run("echo "+print_string);
								}
								break;
							case "mkdir":
								run(entered_cmd);
								break;
							case "cd":
								if (whole_entered_cmd.length==2){
									//correct
									cd(whole_entered_cmd[1]);
								}
								break;
							case "pwd":
								System.out.println(pwd());
								break;
						}
					}
				}
				else{
					System.out.println("Goodbye!");
				}
			}while(!entered_cmd.equals("exit"));
		}
		else{
			System.out.println("System has no console.Email <adeebnqo@gmail.com> for more help.");
			System.exit(0);
		}
	}
	/*
	THE FOLLOWING METHOD(S) REPRESENT THE IMPLEMENTATION OF EACH CMD. THEY USE (RUNTIME/PROCESSBUILDER).
	THEY HAVE BEEN IMPLEMENTED BECAUSE OF WHAT T.A HAS E-MAILED ME

	<e-mail>
		<sender>
		Dominic Follett-Smith
			<address>
			dominicfollett@gmail.com 
			</address>
		</sender>
		
		<message>
		Hi, you do not need to re-implement standard programs such as grep
		etc, all you have to do is run the command, and display the output -
		or check that it has done its job
		</message>
	</e-mail>
	
	(EACH) METHOD WILL RUN, AND RETURN THE RESULT OF THE COMMAND IF IT HAS ONE (MAY IT BE ERROR OR SUCCESS)
	*/
	
	//Method for running a cmd
	public static void run(String cmd){
		try{
			Process proc = Runtime.getRuntime().exec(cmd);
			//Output of the cmd
			InputStream out = proc.getInputStream();
			Scanner out_stream =new Scanner(out);
			while(out_stream.hasNextLine()){
				System.out.println(out_stream.nextLine());
			}
			//Errors of the cmd
			InputStream in = proc.getErrorStream();
			Scanner error_stream = new Scanner(in);
			while(error_stream.hasNextLine()){
				System.out.println(error_stream.nextLine());
			}
		}catch(Exception e){
			String reason = e.getMessage();
			System.out.println(reason);
		}
	} 
	
	/*
	______________________________________________________________________________________________________
	THE FOLLOWING METHODS REPRESENT MY IMPLEMENTATION OF THE CMD'S. THEY DO NOT USE RUNTIME OR THE
	PROCESSBUILDER. THEY WERE THE METHODS I WAS USING AT FIRST --- DUE TO BEING CONFUSED BY ASSIGNMENT
	DESCRIPTION
	
	FINISHED METHODS:
	-mkdir();
	-createDir();
	-ls();
	-pwd();
	-echo();

	UNFINISHED METHODS:
	-cd();
	A user cannot use relative paths. The user can however 'cd' backwards using  'cd ..'
	
	-grep();
	Two implementations of grep() 'grep' are currently here.
	The missing one has the following method signature : public static void grep(String arg);

	-clear();
	This method currently removes all lines before the current line. It does not, however, move the current
	line to the top of the console.
	_______________________________________________________________________________________________________
	*/
	//Method for the mkdir command
	public static boolean mkdir(String folder_name){
		boolean condition = false;
		File new_folder = new File(folder_name);
		if (new_folder.exists()&&(new_folder.isDirectory())){
			//if there is already a folder with that name
			String reply;
			int bit=0;
			do{
				System.out.println("Directory exists: override it? (y/n)");
				reply = terminal.readLine();
				if (reply.equals("n")||(reply.equals("y"))){
					bit =1;
				}
			}while(bit==0);
			//if user finally decides to enter eithr "y" or "n"
			if (reply.equals("y")){
				condition = createDir(folder_name);
			}
			return condition;
		}
		else{
			//creating the folder, provided it didn't already exists
			condition = createDir(folder_name);
			return condition;
		}
	}
	//Method to assist mkdir(), written to avoid code duplication
	public static boolean createDir(String folder_name){
		boolean condition = (new File(folder_name)).mkdir();
		return condition;
	}
	//Method for listing contents of a directory
	public static Vector<String> ls(String dir){
		Vector<String> items_in_directory = new Vector<String>();
		File given_directory = new File(dir);
		boolean existance_of_directory = given_directory.exists();
		if (existance_of_directory){
			if (given_directory.isDirectory()){
				File[] subfiles = given_directory.listFiles();
				for (File current_file:subfiles){
					String filenames = current_file.toString();
					if (current_file.isDirectory()){
						items_in_directory.add("\033[31m "+filenames.replace(dir+"/","")+"\033[0m");
					}else{
						items_in_directory.add(filenames.replace(dir+"/",""));
					}
				}
			}else{
				items_in_directory.add(dir);
			}
		}
		else{
			//directory does not exist
			items_in_directory.add(dir+": No such file or directory");
		}
		return items_in_directory;
		/*
		-could loose important files because of this
		-deprecated
		given_directory.delete();
		*/
	}
	//Method for getting working directory
	public static String pwd(){
		return working_directory;
	}
	//Method for the cmd "echo"
	public static String echo(String param){
		return param;
	}
	//Method for clearing the console window
	public static void clear(){
		final String ESC = "\033[";
		System.out.print(ESC + "2J"); 
	}
	//The following implementations are for the cmd "grep"

	//This grep searches a textfiles
	public static Vector<String> grep(String word, String text_file_name){
		try{
			Vector<String> lines = new Vector<String>();
			Scanner file = new Scanner(new FileInputStream(text_file_name));
			while(file.hasNextLine()){
				String line = file.nextLine();
				if (line.contains(word)){
					lines.add(line.replaceAll(word,"\033[31m"+word+"\033[0m"));
				}
			}
			lines.trimToSize();
			return lines;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	//This grep works with a vector
	public static Vector<String> grep(String word,Vector<String> container){
		Vector<String> matches = new Vector<String>();
		for (String current_item:container){
			if (current_item.contains(word)){
				matches.add(current_item.replaceAll(word,"\033[31m"+word+"\033[0m"));
			}
		}
		if (matches.size()==0){
			return null;
		}
		matches.trimToSize();
		return matches;
	}
	//Method for changing the directory
	public static void cd(String dir){
		File new_dir = new File(dir);
		if (new_dir.exists()){
			if (new_dir.isDirectory()){
				if (dir.equals("..")){
					//if you are in the / directory
					if (working_directory.equals("/")){
						//do nothing -- you cannot move backwards anymore
					}
					else{
						File back_dir = new File(working_directory+"/..");
						String prev_dir = back_dir.getAbsoluteFile().getParentFile().getParentFile().toString();
						if (prev_dir==null){
							working_directory = "/";
						}
						else{
							working_directory=prev_dir;
						}
					}
				}
				else if (!dir.equals(".")){
					working_directory = dir;
				}
				else if (dir.startsWith("./")){
					run("cd "+dir);
				}
			}
			else{
				System.out.println(dir+":  Not a directory.");
			}
		}
		else{
			File tmp_dir = new File(working_directory);
			File move_to_dir = new File(tmp_dir.getAbsolutePath()+"/"+dir);
			if (move_to_dir.exists() && move_to_dir.isDirectory()){
				String my_dir = move_to_dir.getAbsolutePath();
				working_directory = my_dir;
                        }
			else{
				//directory does not exist
				System.out.println(dir+":  No such file or directory.");
			}
		}
	}
}
