package view;

import domain.card.Card;
import domain.gamer.*;

import java.util.stream.Collectors;

public class OutputView {
    private static final String DELIMITER = ",";
    private static final String NEWLINE = "\n";

    public static void printPlayerNamesGuide() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public static void printInitCardGuide(Gamers gamers) {
        StringBuffer initCardGuide = new StringBuffer();
        initCardGuide.append(NEWLINE)
                .append("딜러와 ")
                .append(gamers.getPlayers()
                        .stream()
                        .map(Gamer::getName)
                        .collect(Collectors.joining(DELIMITER)))
                .append(" 에게 카드 2장을 나누었습니다.");
        System.out.println(initCardGuide.toString());
    }

    public static void printGamersCard(Gamers gamers) {
        System.out.println(printDealerCard(gamers.getDealer()));
        gamers.getPlayers().forEach(OutputView::printGamerCard);
        System.out.println();
    }

    public static void printGamerCard(Gamer gamer) {
        System.out.println(printCards(gamer));
    }

    private static String printDealerCard(Dealer dealer) {
        StringBuilder cardsToString = new StringBuilder();
        cardsToString.append(dealer.getName())
                .append(" : ")
                .append(printCard(dealer.getCards().get(0)));
        return cardsToString.toString();
    }

    private static String printCards(Gamer gamer) {
        StringBuilder cardsToString = new StringBuilder();
        cardsToString.append(gamer.getName())
                .append(" : ")
                .append(gamer.getCards()
                        .stream()
                        .map(OutputView::printCard)
                        .collect(Collectors.joining(", ")));
        return cardsToString.toString();
    }

    private static String printCard(Card card) {
        StringBuilder cardToString = new StringBuilder();

        cardToString.append(card.getCardNumber().getCardInitial())
                .append(card.getCardSuit().getSuit());
        return cardToString.toString();
    }

    public static void printAddCardAtDealer() {
        System.out.println("딜러는 17이상이 될 때까지 카드를 더 받았습니다.");
    }

    public static void printBettingMoneyGuide(String playerName) {
        System.out.printf("%s의 배팅 금액은? %s", playerName, NEWLINE);
    }

    public static void printAddCardGuide(Player player) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%s", player.getName(), NEWLINE);
    }

    public static void printCardsResultAndScore(CardsResult cardsResult) {
        System.out.println();
        cardsResult.getGamersCardResult()
                .forEach((gamer, score) -> System.out.println(printCards(gamer) + " - 결과 : " + score));
    }

    public static void printTotalEarningResult(GameResult gameResult) {
        System.out.println();
        System.out.println("### 최종 수익");
        System.out.println("딜러 : " + gameResult.getDealerEarning().getMoney());

        gameResult.getPlayersTotalEarning()
                .forEach((player, money) -> System.out.println(player.getName() + " : " + money.getMoney()));
    }
}