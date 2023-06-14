import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LeagueManager
{
    private List<Team> teams;
    private List<Match> matches;

    public LeagueManager()
    {
        teams = new ArrayList<>();
        matches = new ArrayList<>();
    }

    public List<Team> getTeams() {
        return teams;
    }

    public List<Match> getMatches() {
        return matches;
    }
    public void readTeamNamesFromFile(String fileName) {
        try {
            //Path x = Paths.get(fileName);
            Path path = Paths.get(fileName);
            List<String> lines = Files.readAllLines(path);

            teams = lines.stream()
                    .map(line -> {
                        String[] parts = line.split(",");
                        int groupId = Integer.parseInt(parts[0]);
                        String groupName = parts[1];
                        return new Team(groupId, groupName, generateRandomPlayers());
                    })
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


   /* public void readTeamNamesFromFile(String fileName) {
        try (Stream<String> lines = Files.lines(Paths.get(fileName)))
        {
            teams = lines.map(line -> new Team(line.hashCode(), line, generateRandomPlayers()))
                    .collect(Collectors.toList());
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }*/
    public static int generateRandomIntFromUUID() {
        UUID uuid = UUID.randomUUID();
        int randomInt = uuid.hashCode();
        return randomInt;
    }

    public static List<Player> generateRandomPlayers()
    {
        List<Player> players = IntStream.range(0, 15)
                .mapToObj(i -> new Player(generateRandomIntFromUUID(), Utils.getRandomName(Utils.PRIVET_NAME),Utils.getRandomName(Utils.FAMILY_NAME)))
                .collect(Collectors.toList());
        return players ;
    }

    public void generateMatchSchedule() {
        List<Team> shuffledTeams = new ArrayList<>(teams);
        Collections.shuffle(shuffledTeams);

        for (int round = 0; round < 9; round++) {
            for (int i = 0; i < shuffledTeams.size(); i += 2) {
                Team homeTeam = shuffledTeams.get(i);
                Team awayTeam = shuffledTeams.get(i + 1);

                matches.add(new Match( homeTeam, awayTeam, new ArrayList<>()));
            }
            Collections.rotate(shuffledTeams.subList(1, shuffledTeams.size()), 1);
        }
    }


    public void playMatches() {
        Random random = new Random();
        for (Match match : matches) {
            int homeTeamGoals = random.nextInt(6);
            int awayTeamGoals = random.nextInt(6);
            for (int i = 0; i < homeTeamGoals; i++) {
                int minute = random.nextInt(90) + 1;
                Player scorer = match.getHomeTeam().getPlayers().get(random.nextInt(15));
                scorer.addGoal(new Goal(match.getGoals().size(), minute, scorer));
                match.getGoals().add(new Goal(match.getGoals().size(), minute, scorer));
            }
            for (int i = 0; i < awayTeamGoals; i++) {
                int minute = random.nextInt(90) + 1;
                Player scorer = match.getAwayTeam().getPlayers().get(random.nextInt(15));
                scorer.addGoal(new Goal(match.getGoals().size(), minute, scorer));
                match.getGoals().add(new Goal(match.getGoals().size(), minute, scorer));
            }
            if (homeTeamGoals > awayTeamGoals)
            {
                match.getHomeTeam().setPoints(3);
            }
            else if(homeTeamGoals < awayTeamGoals)
            {
                match.getAwayTeam().setPoints(3);
            }
            else
            {
                match.getHomeTeam().setPoints(1);
                match.getAwayTeam().setPoints(1);
            }
        }
    }

    public List<Match> findMatchesByTeam(int teamId) {
        return matches.stream()
                .filter(match -> match.getHomeTeam().getId() == teamId || match.getAwayTeam().getId() == teamId)
                .collect(Collectors.toList());
    }

    public List<Team> findTopScoringTeams(int n) {
        return teams.stream()
                .sorted(Comparator.comparingInt(team -> team.getGoalsScored().size()))
                .limit(n)
                .collect(Collectors.toList());
    }
    public List<Player> findPlayersWithAtLeastNGoals(int n) {
        return teams.stream()
                .flatMap(team -> team.getPlayers().stream())
                .filter(player -> player.getGoalsScored().size() >= n)
                .collect(Collectors.toList());
    }

    public Team getTeamByPosition(int position) {
        return teams.stream()
                .sorted(Comparator.comparingInt(team -> team.getPoints()))
                .skip(position - 1)
                .findFirst()
                .orElse(null);
    }

    public Map<Integer, Integer> getTopScorers(int n) {
        return teams.stream()
                .flatMap(team -> team.getPlayers().stream())
                .collect(Collectors.groupingBy(Player::getId, Collectors.summingInt(player -> player.getGoalsScored().size())))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(n)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }
}

