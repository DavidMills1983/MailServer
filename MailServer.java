
import java.util.Objects;
import java.util.Scanner;

    public class MailServer {
        public static void main(String[] args) {
            Scanner input = new Scanner(System.in);
            System.out.println("Welcome to My Mail Server!");
            String returnAddress = "";
            String destinationAddress = "";
            String emailBody = "";

            int state = 0;

            while(true){
                String text = input.nextLine();
                if (text.equals("QUIT")){
                    System.out.println("Bye");
                    break;
                    }

                switch(state) {
                    case 0:
                         if (text.startsWith("MAIL FROM: ")){
                            if(isEmailValid(text)){
                                returnAddress = text.substring(text.indexOf(":")+2);
                                System.out.println("OK");
                                state = 1;

                            }
                            else {
                                System.out.println("Invalid email address");
                            }
                        } else {
                            System.out.println("Invalid command");
                        }
                    break;

                    case 1:
                        if (text.startsWith("RCPT TO: ")){
                            if(isEmailValid(text)){
                                destinationAddress = text.substring(text.indexOf(":")+2);
                                System.out.println("OK");
                                state = 2;
                            }
                            else {
                                System.out.println("Invalid email address");
                            }
                        } else {
                            System.out.println("Invalid command");
                        }
                    break;

                    case 2:
                        if (text.equals("DATA")) {
                            while (true){
                                String email = input.nextLine();
                                if (email.equals(".")) {
                                    System.out.println("Sending email...");
                                    System.out.println("from: " + returnAddress);
                                    System.out.println("to: " + destinationAddress);
                                    System.out.println(emailBody);
                                    System.out.println("...done!");
                                    state = 0;
                                    break;
                                } else {
                                    emailBody = emailBody + "\n" + email;
                                }
                            }
                        }
                    break;
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


