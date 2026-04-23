import java.util.Scanner;
import java.util.PriorityQueue;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.io.File;

public class Main {

    // helper methods for reading files
    public static PlayerCharacter sCharacter(Scanner scanFile, int id, String applyStyle) {
        String line = scanFile.nextLine();
        Scanner lineScanner = new Scanner(line);
        if (!lineScanner.hasNextInt())
            throw new NoSuchElementException();
        int a = lineScanner.nextInt();
        if (!lineScanner.hasNextInt())
            throw new NoSuchElementException();
        int b = lineScanner.nextInt();
        PlayerCharacter player = new PlayerCharacter(a, b, id);
        int priority = (applyStyle.equalsIgnoreCase("srf")) ? b : a;
        player.setPriority(priority);
        return player;
    }

    public static void main(String[] args) throws IOException {
        boolean readingFile = true;
        Scanner inputScanner = new Scanner(System.in);
        while (readingFile) {
            System.out.println("Enter Queueing Style: ");
            String qStyle = inputScanner.nextLine();

            // checking for misspelled policy
            if (!qStyle.equalsIgnoreCase("fcfs") && !qStyle.equalsIgnoreCase("srf")) {
                System.out.println("Policy should be either fcfs or srf");
                continue;
            }

            // requesting input file name
            System.out.println("Enter input file:");
            String fileName = inputScanner.nextLine();

            Scanner fileScanner = null;

            // checking for error while opening the file
            try {
                fileScanner = new Scanner(new File(fileName));
            } catch (FileNotFoundException e) {
                System.out.println("Can not open file " + fileName);
                continue;
            }

            // READING the content of the file and storing the value in prioriy queue
            PriorityQueue<PlayerCharacter> heap = new PriorityQueue<PlayerCharacter>();
            int charId = 0;
            PlayerCharacter inCave = null;
            if (fileScanner != null) {
                int waitingTime = 0;
                int totalPlayers = 0;

                // checking for error while reading elements
                try {
                    PlayerCharacter player = null;
                    if (fileScanner.hasNextLine()) {
                        player = sCharacter(fileScanner, charId++, qStyle);
                        totalPlayers++;
                    }
                    System.out.println();
                    System.out.println("Time          Event");
                    System.out.println("-------        --------");
                    System.out.println();
                    for (int time = 0; player != null || !heap.isEmpty() || inCave != null; time++) {
                        if (player != null && time == player.getArrivalTime()) {
                            heap.add(player);
                            System.out.println(time + " Character " + player.getCharacterNumber() + " enters queue");
                            if (fileScanner.hasNextLine()) {
                                player = sCharacter(fileScanner, charId++, qStyle);
                                totalPlayers++;
                            } else {
                                player = null;
                            }
                        }

                        if (inCave != null && time == inCave.getDepartureTime()) {
                            int departId = inCave.getCharacterNumber();
                            inCave = null;
                            System.out.println(time + " Character " + departId + " leaves cave");
                        }

                        if (inCave == null && !heap.isEmpty()) {
                            inCave = heap.poll();
                            inCave.setDepartureTime(time + inCave.getRequestTime());
                            waitingTime = waitingTime + (time - inCave.getArrivalTime());
                            System.out.println(time + " Character " + inCave.getCharacterNumber() + " enters cave");
                        }

                    }
                } catch (NoSuchElementException b) {
                    System.out.println("Error in input file");
                    continue;
                }

                double avgWaitingTime = (double) waitingTime / totalPlayers;
                System.out.println();
                System.out.println("Simulation over...");
                System.out.println("Average wait time: " + avgWaitingTime);
                System.out.println();
            }

            readingFile = false;

        }

    }

}
