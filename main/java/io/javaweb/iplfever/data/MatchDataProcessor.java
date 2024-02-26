package io.javaweb.iplfever.data;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.javaweb.iplfever.model.Match;
import org.springframework.batch.item.ItemProcessor;
/* takes MatchInput class (which consumes data from csv file) as input, processes it
 and produces o/p to the output Match class data(which we want)
*/
public class MatchDataProcessor implements ItemProcessor<MatchInput, Match> {

    private static final Logger log = LoggerFactory.getLogger(MatchDataProcessor.class);

    @Override
    public Match process(final MatchInput matchInput) throws Exception {
        Match match = new Match();
        match.setId(Long.parseLong(matchInput.getId()));
        match.setCity(matchInput.getCity());

        match.setDate(LocalDate.parse(matchInput.getDate()));
        match.setPlayerOfMatch(matchInput.getPlayer_of_match());
        match.setVenue(matchInput.getVenue());

        String firstInningsteam, secondInningsTeam;
        //Team1 is the firstInningsteam (the one who bats first)
        if (matchInput.getToss_decision().equalsIgnoreCase("bat")) {
            firstInningsteam = matchInput.getToss_winner();
            secondInningsTeam = matchInput.getToss_winner().equals(matchInput.getTeam1())?
             matchInput.getTeam2() : matchInput.getTeam1();
            log.info(firstInningsteam+"-"+secondInningsTeam);
        } else {
            secondInningsTeam = matchInput.getToss_winner();
            firstInningsteam = matchInput.getToss_winner().equals(matchInput.getTeam1())?
             matchInput.getTeam2() : matchInput.getTeam1();
            log.info(firstInningsteam+"-"+secondInningsTeam);
        }
        match.setTeam1(firstInningsteam);
        match.setTeam2(secondInningsTeam);

        match.setTossWinner(matchInput.getToss_winner());
        match.setTossDecision(matchInput.getToss_decision());
        match.setMatchWinner(matchInput.getWinner());
        match.setResult(matchInput.getResult());
        match.setResultMargin(matchInput.getResult_margin());

        match.setUmpire1(matchInput.getUmpire1());
        match.setUmpire2(matchInput.getUmpire2());
        return match;
    }

}