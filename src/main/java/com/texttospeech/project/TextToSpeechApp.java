package com.texttospeech.project;


import java.util.Arrays;
import java.util.List;

public class TextToSpeechApp {
    public static  void main(String args[]){

        TextToSpeechService tts = new TextToSpeechService();

        //System.out.println("*******************ENTER WORD HERE*******************");
        //Scanner word = new Scanner(System.in);

        List<String> arrayList = Arrays.asList("Are you mad bro", "I am programmer", "Bro pliz tell me i am programmer ok?", "But programmer is ok remember" +
                "\"Did you see that?\" Joe said to his friend Bill.\n" +
                "\"You're a great shooter!\"\n" +
                "Bill caught the basketball and bounced it before\n" +
                "throwing it again. The ball flew into the net.\n" +
                "\"Bill, you never miss!\" Joe said admiringly.\n" +
                "\"Unless I'm in a real game,\" Bill complained.\n" +
                "\"Then I miss all the time.\"\n" +
                "Joe knew that Bill was right. Bill performed much\n" +
                "better when he was having fun with Joe in the school\n" +
                "yard than he did when he was playing for the school\n" +
                "team in front of a large crowd.\n" +
                "\"Maybe you just need to practice more,\" Joe suggested.\n" +
                "\"But I practice all the time with you!\" Bill objected.\n" +
                "He shook his head. \"I just can't play well when people are\n" +
                "watching me.\"\n" +
                "\"You play well when I'm watching,\" Joe pointed out.\n" +
                "\"That's because I've known you since we were five\n" +
                "years old,\" Bill said with a smile. \"I'm just not\n" +
                "comfortable playing when other people are around.\"");
        //List<String> arrayList = Arrays.asList(word.nextLine());

        //Loop infinitely

            arrayList.forEach(read -> tts.speak(read, 2.0f, false, true));

        //Loop infinitely
//                for (int i = 0; i < 150.000; i++)
//                    arrayList.forEach(readWordOut -> tts.speak(readWordOut, 2.0f, false, true));

            }
}
