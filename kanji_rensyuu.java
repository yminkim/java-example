// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

//1. 일본어 기초 한자를 공부하는 게임
//2. 일단 챕터는 3개로 각 5개의 단어가 있음
//3. 해당 챕터 플레이 시
//- 선택에 따라 동작
//ㄴ 힌트(뜻, 히라가나) 표시
//ㄴ 모름
//ㄴ 알고있음
//
//- 단어 등장 순서는 랜덤
//- 이미 알고있음으로 선택한 단어는 등장하지 않음
//4. 완료 시
//- 아직 모르는 단어가 있는경우 완료가 될 때 까지 모르는 것만 나옴
//- 모두 아는 것으로 선택한 경우
//ㄴ 챕터의 회독 횟수가 카운트 됨
//ㄴ 해당 챕터는 다시 '모두 모름' 상태로 초기화 됨


import static java.lang.Thread.sleep;
import java.util.*;
import java.util.Scanner;


class Chapter {
    int completed_count;
    String[][] word_arr;
    int chap_num;
    Chapter(int completed_count, String[][] word_arr, int chap_num) {
        this.completed_count = completed_count;
        this.word_arr = word_arr;
        this.chap_num = chap_num;
    }
}


public class Main {

    public static void print(String text) {
        System.out.println(text);

    }

    public static void clear() {
        for (int i=0; i<20; i++)
            System.out.println();
    }
    public static void game_play(Chapter chapter) {


        Scanner sc = new Scanner(System.in);
        // 2차원 배열을 List에 담기
        List<String[]> chap_1List = new ArrayList<>(Arrays.asList(chapter.word_arr));

        // 리스트를 랜덤하게 섞음
        Collections.shuffle(chap_1List);

        // 다시 배열로 변환
        chapter.word_arr = chap_1List.toArray(new String[chap_1List.size()][]);

        int count_num =0;

        for(int i=0; i<5; i++) {
            if(chapter.word_arr[i][0] == "1") {
                count_num +=1;
            }
        }

        int copy_count_num = count_num;
        int num =0;
        for(int index =0; index<5; index++) {


            if(chapter.word_arr[index][0] == "0") {
                num +=1;
                boolean end_condition = true;
                boolean hint1 = false;
                boolean hint2 = false;

                while(end_condition) {
                    clear();

                    System.out.printf("%d장 문제(%d/5): %s\n",chapter.chap_num, copy_count_num+num, chapter.word_arr[index][1]);

                    if(hint2 == true)
                        System.out.printf("*%s\n", chapter.word_arr[index][3]);
                    if(hint1 == true)
                        System.out.printf("*%s\n", chapter.word_arr[index][2]);


                    print("1. 뜻표기  2. 히라가나\n3. 모름   4. 알고있음");
                    String menu = sc.next();
                    switch (menu) {
                        case "1":
                            hint2 = true;
                            break;
                        case "2":
                            hint1 = true;
                            break;
                        case "3" :
                            end_condition = false;
                            break;
                        case "4":
                            chapter.word_arr[index][0] = "1";
                            count_num +=1;
                            if(count_num == 5) {
                                for(int o=0; o<5; o++) {
                                    chapter.word_arr[o][0] = "0";
                                }
                                chapter.completed_count +=1;
                                return;
                            }
                            end_condition = false;
                            break;
                        case "5" :
                            for(String[] row : chapter.word_arr) {
                                System.out.println(Arrays.toString(row));
                            }
                            break;
                        default:
                            break;
                    }
                }

            }
            else {}

        }

        return;
    }

    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        String[][] chap_1 = {
                {"0", "春", "はる", "봄"},
                {"0", "夏", "なつ", "여름"},
                {"0", "秋", "あき", "가을"},
                {"0", "冬", "ふゆ", "겨울"},
                {"0", "季節", "きせつ", "계절"}
        };

        String[][] chap_2 = {
                {"0", "風", "かぜ", "바람"},
                {"0", "雪", "ゆき", "눈"},
                {"0", "学校", "がっこう", "학교"},
                {"0", "魚類", "ぎょるい", "어류"},
                {"0", "問題", "もんだい", "문제"}
        };

        String[][] chap_3 = {
                {"0", "天気", "てんき", "날씨"},
                {"0", "海", "うみ", "바다"},
                {"0", "大学", "だいがく", "대학"},
                {"0", "学院", "がくいん", "학원"},
                {"0", "自動", "じどう", "자동"}
        };

        Chapter chapter1 = new Chapter(0, chap_1, 1);
        Chapter chapter2 = new Chapter(0, chap_2, 2);
        Chapter chapter3 = new Chapter(0, chap_3, 3);

        boolean exit = true;
        while(exit) {
            clear();
            System.out.println("일본어 기초 한자 공부하기\n");
            System.out.printf("1. 1장(%d회 완료)\n2. 2장(%d회 완료)\n3. 3장(%d회 완료)\n\n0. 종료\n입력: ", chapter1.completed_count, chapter2.completed_count, chapter3.completed_count);
            String menu = sc.next();
            switch (menu) {
                case "1" :
                    game_play(chapter1);
                    break;
                case "2" :
                    game_play(chapter2);
                    break;
                case "3" :
                    game_play(chapter3);
                    break;
                case "0" :
                    exit = false;
                    break;
                default:
                    break;
            }
        }

//        for(String[] row : chap_1) {
//            System.out.println(Arrays.toString(row));
//        }

        // 1장, 2장, 3장 어디로 갈지 정하기



    }

}
