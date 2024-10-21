
import java.util.Objects;
import java.util.Scanner;

    public class MailServer {
        public static void main(String[] args) {
            Scanner input = new Scanner(System.in);
            System.out.println("Welcome to My Mail Server!");
            String returnAddress = "";
            String destinationAddress = "";
            String emailBody = "";
            boolean secondCommand = false;
            boolean thirdCommand = false;

            // first command MAIL FROM

            while(true){

                String text = input.nextLine();

                if (Objects.equals(text, "QUIT")){
                    System.out.println("Bye");
                    break;
                } else if (text.startsWith("MAIL FROM: ")){

                    if(isEmailValid(text)){
                        returnAddress = text.substring(text.indexOf(":")+2);
                        System.out.println("OK");
                        secondCommand = true;
                        break;
                    }
                    else {
                        System.out.println("Invalid email address");
                    }
                } else {
                    System.out.println("Invalid command");
                }
            }

            // second command - checking for RCPT

            while (secondCommand){

                String text = input.nextLine();

                if (Objects.equals(text, "QUIT")){
                    System.out.println("Bye");
                    break;
                } else if (text.startsWith("RCPT TO: ")){
                    if(isEmailValid(text)){
                        destinationAddress = text.substring(text.indexOf(":")+2);
                        System.out.println("OK");
                        thirdCommand = true;
                        break;
                    }
                    else {
                        System.out.println("Invalid email address");
                    }
                } else {
                    System.out.println("Invalid command");
                }
            }

            // third command

            while (thirdCommand){

                String text = input.nextLine();
                if (Objects.equals(text, "QUIT")){
                    System.out.println("Bye");
                    break;
                } else if (Objects.equals(text, "DATA")) {
                    boolean writingEmail = true;
                    while (writingEmail) {
                        String email = input.nextLine();
                        if (Objects.equals(email, "QUIT")) {
                            break;
                        } else if (Objects.equals(email, ".")) {
                            System.out.println("Sending email...");
                            System.out.println("from: " + returnAddress);
                            System.out.println("to: " + destinationAddress);
                            System.out.println(emailBody);
                            System.out.println("...done!");
                            break;
                        } else {
                            emailBody = emailBody + "\n" + email;
                        }
                    }
                }
            }
        }


        public static boolean isEmailValid(String text){

            String address = text.substring(text.indexOf(":")+2);
            int atCount = 0;
            if (address.charAt(0)=='@' || address.charAt(address.length()-1)=='@') {
                return false;
            }

            for(int i = 0; i < address.length(); i++) {
                if (address.charAt(i) == '@') {
                    atCount++;
                }
            }

            if (atCount != 1){
                return false;
            }

            return true;
        }
    }


