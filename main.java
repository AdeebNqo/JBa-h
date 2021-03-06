/*
Copyright (c) 2013 Zola Mahlaza <adeebnqo@gmail.com>. All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are
met:

   * Redistributions of source code must retain the above copyright
notice, this list of conditions and the following disclaimer.
   * Redistributions in binary form must reproduce the above
copyright notice, this list of conditions and the following disclaimer
in the documentation and/or other materials provided with the
distribution.
   * Neither the name of Google Inc. nor the names of its
contributors may be used to endorse or promote products derived from
this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
import java.util.LinkedList;
import java.util.EmptyStackException;
import java.io.Console;
import java.io.File;
import java.util.Vector;
import java.util.Scanner;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.io.OutputStream;
import java.io.InputStream;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.Random;
import java.util.Queue;
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
			System.out.println("Welcome to JBa$h");
			getWelcomeArt();
			System.out.println();
			String tmp_exit_value;
			do{
				System.out.print(username+"@JBa$h:>");
				entered_cmd = terminal.readLine();
				entered_cmd = entered_cmd.replaceAll("^\\s+","");//removing leading whitespace
				tmp_exit_value = entered_cmd.replaceAll("\\s","");
				if (!tmp_exit_value.equals("exit")){
					//if cmd does not contain pipes or "and(&)"
					if ((!entered_cmd.contains("|")) &&(!entered_cmd.contains(";"))){
						run_individual(entered_cmd);
					}
					//When commands are fancy
					else if (entered_cmd.contains(";")){
						run_anded(entered_cmd);
					}
					else if (entered_cmd.contains("|")){
						run_piped(entered_cmd);
					}
					else{
						run(entered_cmd);
					}
				}
				else{
					System.out.println("Goodbye!");
				}
			}while(!tmp_exit_value.equals("exit"));
		}
		else{
			System.out.println("System has no console.Email <adeebnqo@gmail.com> for more help.");
			System.exit(0);
		}
	}
	/*
	Method for printing the welcome art
	*/
	public static void getWelcomeArt(){
		Random gen = new Random();
		int choice = gen.nextInt(5);
		switch(choice){
			case 0:
				System.out.println("       _..--'''@   \033[31m@\033[0m'''--.._");
                        	System.out.println("     .'   \033[31m@\033[0m_/-//-\\/>/>'/ \033[31m@\033[0m  '.");
                        	System.out.println("    (  \033[31m@\033[0m  /_<//<'/----------^-)");
                        	System.out.println("    |'._  \033[31m@\033[0m     //|\033[41m###########\033[0m|");
                        	System.out.println("    |~  ''--..@|',|}}}}}}}}}}}|");
                        	System.out.println("    |  ~   ~   |/ |\033[41m###########\033[0m|");
                        	System.out.println("    | ~~  ~   ~|./|{{{{{{{{{{{|");
                        	System.out.println("     '._ ~ ~ ~ |,/`````````````");
                        	System.out.println("        ''--.~.|/");
				break;
			case 1:
				System.out.println("           (                 ,&&&.");
             			System.out.println("            )                .,.&&");
            			System.out.println("           (  (              \\=__/");
                		System.out.println("               )             ,'-'.");
          			System.out.println("         (    (  ,,      _.__|/ /|");
           			System.out.println("          ) /\\ -((------((_|___/ |");
         			System.out.println("        (  // | (`'      ((  `'--|");
         			System.out.println("        -.;_/ \\--._      \\ \\-._/.");
      				System.out.println("     (_;-// | \\ \\-'.\\    <_,\\_\\`--'|");
      				System.out.println(" 	 ( `.__ _  ___,')      <_,-'__,'");
 				System.out.println("jrei  `'(_ )_)(_)_)'");

				break;
			case 2:
				System.out.println("Bungee Jumping");
				System.out.println("=-=-=-=-=-=-=-=-=-)-=-=-=-=-=-=-=-=-=-");
				System.out.println("_| |__| |________(_________| |__| |___");
				System.out.println(" \\  \\/  /         )        \\  \\/  /");
				System.out.println("  )    (         (          )    (");
				System.out.println("  |    |         (          |    |");
				System.out.println("  |    |          \\|        |    |");
				System.out.println("  |    |           \\o       |    |");
				System.out.println("  |    |           ( \\      |    |");
				System.out.println("  |    |                    |    |");
				System.out.println("  |    |                    |    |");
				System.out.println("  |    |                    |    |Zot");
				System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
				break;
			case 3:
				System.out.println("              a8888b.");
             			System.out.println("             d888888b.");
             			System.out.println("             8P\"YP\"Y88 ");
             			System.out.println("             8|o||o|88 ");
             			System.out.println("             8'    .88 ");
             			System.out.println("             8`._.' Y8. ");
            			System.out.println("            d/      `8b. ");
           			System.out.println("           dP   .    Y8b. ");
          			System.out.println("          d8:'  \"  `::88b ");
         			System.out.println("         d8\"         'Y88b ");
        			System.out.println("        :8P    '      :888 ");
         			System.out.println("         8a.   :     _a88P ");
       				System.out.println("      ._/\"Yaa_:   .| 88P| ");
  				System.out.println(" jgs  \\    YP\"    `| 8P  `.");
  				System.out.println("  a:f  /     \\.___.d|    .' ");
       				System.out.println("       `--..__)8888P`._.' ");
				break;
			case 4:
				System.out.println("__________________|      |____________________________________________");
				System.out.println("     ,--.    ,--.          ,--.   ,--.");
				System.out.println("    |oo  | _  \\  `.       | oo | |  oo|");
				System.out.println("o  o|~~  |(_) /   ;       | ~~ | |  ~~|o  o  o  o  o  o  o  o  o  o  o");
				System.out.println("    |/\\/\\|   '._,'        |/\\/\\| |/\\/\\|");
				System.out.println("__________________        ____________________________________________");
                  		System.out.println("                  |      |dwb");

				break;
		}
		
	}
	/*
	Method for running individual processes/cmds
	*/
	public static void run_individual(String entered_cmd){
		//starting by removing leading whitespace
		entered_cmd = entered_cmd.replaceAll("^\\s+","");
		String[] whole_entered_cmd = entered_cmd.split("\\s+");
		Queue<String> q = new LinkedList<String>();
		for (String val: whole_entered_cmd){
			q.offer(val);
		}
		
		//Running the cmd
		String cmd =null;
		String flags =null;
		String path =null;
		
		cmd = q.poll();
		String tmp;
		if (q.peek()!=null){
			tmp = q.poll();
			if (tmp.startsWith("-")){
				flags = tmp;
				
				//if there is something left on the q
				if (q.peek()!=null){
					path = q.poll();
				}
			}
			else{
				path = tmp;
			}
		}
                switch(cmd){
                	case "clear":
                        	run("clear");
                                break;
                        case "ls":
				//if path is null, we have to use our current directory -- the stored one
                        	if (path==null){
					if (flags!=null){
						run(cmd+" "+flags+" "+working_directory);
					}
					else{
						run(cmd+" "+working_directory);
					}
				}
				//if path has been specified
				else{
					//if its a absolute path
					if (path.startsWith("/")){
						if (flags!=null){
							run(cmd+" "+flags+" "+path);
						}
						else{
							run(cmd+" "+path);
						}
					}
					//if it's a relative path
					else{
						File tmp_file = new File(working_directory+"/"+path);
						if (tmp_file.exists()){
							//if this file exists, list it's contents
							if (flags!=null){
								run(cmd+" "+flags+" "+tmp_file.getAbsolutePath());
							}
							else{
								run(cmd+" "+tmp_file.getAbsolutePath());
							}
						}
						else{
							//print error message
							System.out.println("cannot access "+path+" : No such file or directory");
						}
					}
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
				if (path==null){
					cd("");
				}
				else{
					cd(path);
				}
                                break;
                        case "pwd":
                        	System.out.println(pwd());
                                break;
			default:
				run(entered_cmd);
				break;
            }

	}
	/*
	Method for running ;'d commands
	*/
	public static void run_anded(String cmd){
		String[] whole_cmd = cmd.split(";");
		for (String current_string:whole_cmd){
			if (current_string.contains("|")){
				run_piped(current_string);
			}
			else{
				run_individual(current_string);
			}
		}
	}
	/*
	This method is for running |'d commands
	*/
	String test_cmd;//This is not reall importan
	public static void run_piped(String cmd){
		int last_pos =0;
		Vector<String> commands = new Vector<String>();
		int string_length = cmd.length();
		//spliting up the commands
		for (int i=0;i<string_length;i++){
			if (cmd.charAt(i)=='|'){
				String sub = cmd.substring(last_pos,i);
				last_pos = i;
				commands.add(sub);
			}
		}
		//running the actual commands
		commands.add(cmd.substring(last_pos+1));
		commands.trimToSize();
		ProcessBuilder proc=null;//defining the process outside the loop so that we can can get the results afterwards
		InputStream results = null;
		for (String cur_string:commands){
			cur_string = (cur_string.replaceAll("\\s+"," "));
			cur_string = cur_string.replaceAll("^\\s+","");
			try{
				//formatting the command
				if (cur_string.contains("ls")){
					String[] str = cur_string.split("\\s+");
					if (str.length==1){
						//if cmd is just ls
						cur_string = "ls "+working_directory;
					}
					else if (str.length==2 && str[1].startsWith("-")){
						//ls and flags only, no dir given
						cur_string=cur_string+" "+working_directory;
					}
					else if (str.length==2){
						//no flags given
						if (!str[1].startsWith("/home")){
							//if not full path
							cur_string = "ls "+working_directory+"/"+str[1];
						}
					}
					else if (str.length==3){
				        	if (!str[1].startsWith("/home")){
                                                	//if not full path
                                                	cur_string = str[0]+" "+str[1]+" "+working_directory+"/"+str[2];
                                        	}
					}
				}
				//starting the 'current' process--running the next cmd on the list of pipes
				proc = new ProcessBuilder(cur_string.split("\\s"));
				Process p = proc.start();
				if (results!=null){
					BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
					Scanner results_reader = new Scanner(results);
					while(results_reader.hasNextLine()){
						writer.write(results_reader.nextLine());
						writer.newLine();
					}
					writer.close();
					results_reader.close();
				}
				p.waitFor();
				results = p.getInputStream();
	
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		//printing final output of the piped cmd
		//<here>
		Scanner final_results = new Scanner(results);
		while(final_results.hasNextLine()){
			System.out.println(final_results.nextLine());
		}
	}


	/*
	_______________________________________________________________________________________________________
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
	_________________________________________________________________________________________________________
	*/


	
	//Method for running a cmd
	public static void run(String cmd){
		try{
			ProcessBuilder procBuilder = new ProcessBuilder(cmd.split("\\s+"));
			procBuilder.directory(new File(working_directory));
			Process proc = procBuilder.start();//Runtime.getRuntime().exec(cmd);
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
	DESCRIPTION. THEY ARE MAINLY FOR ASSISTING WHERE RUNTIME/PROCESSBUILDER IS USELESS.
	
	FINISHED METHODS:
	-mkdir();
	-createDir();
	-ls();
	-pwd();
	-echo();

	UNFINISHED METHODS:
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
	/*
	___________________________________________________________________________
	END OF METHODS WHICH ARE UNUSED

	____________________________________________________________________________
	*/

	//Method for changing the directory
	public static void cd(String dir){
		try{
			//Starting by creating the folder
			File tmp;
			if (dir.startsWith("/")){
				tmp = new File(dir);
			}
			else{
				tmp = new File(working_directory+"/"+dir);
			}
			//determining the existance of the folder
			if (tmp.exists() && tmp.isDirectory()){
				String new_path = sanitizePath(tmp.getAbsolutePath());
				working_directory = new_path;
			}
			//if the folder does not exist
			else{
				System.out.println(dir+": No such file or directory");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	//Method for sanitising path when it contains ..
	public static String sanitizePath(String path){
		String[] path_array = path.split("/");
		if (path.contains("..")){
			try{
				Stack<String> tokens = new Stack<String>();
				for (String cur_string:path_array){
					if (!cur_string.equals("")){
						if (cur_string.equals("..")){
							tokens.pop();
						}
						else{
							tokens.push(cur_string);
						}
					}
				}
	
				//Joining the remaining strings
				StringBuilder str = new StringBuilder();
				for (String cur_string:tokens){
					str.append("/"+cur_string);
				}
				path = str.toString();
			}catch(EmptyStackException e){
				return "/";
			}
		}
		if (path.startsWith("./")){
			path = path.substring(1);
		}
		path = path.replaceAll("/\\.","");
		return path;
	}
}
