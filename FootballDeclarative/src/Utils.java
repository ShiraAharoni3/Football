import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;;
public class Utils {
    public static final List<String> PRIVET_NAME = Arrays.asList("Lionel", "Robert", "pillows", "Quinn", "Cristiano", "Thiago", "Angola",
            "Jan", "Alison", "Joshua", "Sadio", "Harry", "Luca", "Tony", "Gigi",
            "Bernardo", "Lautero", "Kaylor", "Arling", "Joao", "Alexander",
            "Thomas", "Leon", "Hugo", "Calido", "Antonio", "Andrew",
            "Marco", "Bruno", "Riyadh");


    public static final List<String> FAMILY_NAME = Arrays.asList(" Messi", "Lewandowski", "Benzema", "Ronaldo", " Courtois", "Oblak", "Beef",
            "Kimich", " Mana", "Coman", " Hernandez", " Fernandez", " Varati",
            "Robertson ", "Roediger", "Coulibaly", "Loris", "Muller", " Goretzka",
            "Arnold", "  Canceled", "Holland", " Dias", " Silva", "Donnaroma",
            " Cross", "Modric", "Barla", "Martins", "Fa");

    public static String getRandomName(List<String> names)
    {
        Random random = new Random();
        int randomIndex = random.nextInt(names.size());
        return names.get(randomIndex);
    }

    public static List<Player> generatePlayers(List<String> firstNames, List<String> lastNames, int count) {
        List<Player> players = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < count; i++)
        {
            int id = i ;
            String firstName = firstNames.get(random.nextInt(firstNames.size()));
            String lastName = lastNames.get(random.nextInt(lastNames.size()));

            Player player = new Player( i,firstName, lastName);
            players.add(player);
        }

        return players;
    }


    public static List<String> ReadCsvFile()
    {
        List<String> teamValue = new ArrayList<>() ;
        try {
            File file = new File("src/footballLeague.csv");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                teamValue.add(String.valueOf(values)) ;
            }

            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(teamValue.size());
        return teamValue ;
    }
}
   // public String readData(File file) {
/*        String data;
        String firstLine;

         try {
          BufferedReader bufferedReader = new BufferedReader(new FileReader());
            firstLine = bufferedReader.readLine();
            while ((data = bufferedReader.readLine()) != null) {
                String[] values = data.split(",");
                //Passenger passenger = new Passenger(values);
                //passengers.add(passenger);
            }
            bufferedReader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
*/  //      return null ;//firstLine;
    //}

