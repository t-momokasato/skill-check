package q003;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
//import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
//import java.util.Collections;
//import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Q003 集計と並べ替え
 *
 * 以下のデータファイルを読み込んで、出現する単語ごとに数をカウントし、アルファベット辞書順に並び変えて出力してください。
 * resources/q003/data.txt
 * 単語の条件は以下となります
 * - "I"以外は全て小文字で扱う（"My"と"my"は同じく"my"として扱う）
 * - 単数形と複数形のように少しでも文字列が異れば別単語として扱う（"dream"と"dreams"は別単語）
 * - アポストロフィーやハイフン付の単語は1単語として扱う（"isn't"や"dead-end"）
 *
 * 出力形式:単語=数
 *
[出力イメージ]
（省略）
highest=1
I=3
if=2
ignorance=1
（省略）

 * 参考
 * http://eikaiwa.dmm.com/blog/4690/
 */
public class Q003 {
    /**
     * データファイルを開く
     * resources/q003/data.txt
     */
    private static InputStream openDataFile() {
        return Q003.class.getResourceAsStream("data.txt");
    }
    
    public static void main(String[] args) throws IOException {
        // ファイルを読み込み集計する
        Map<String, Integer> map = new HashMap<>();
        //テキストをまとめて読み込み実行する
        try ( BufferedReader brffer= new BufferedReader(new InputStreamReader(openDataFile()))){
            String line;
            //
            while ((line = brffer.readLine()) != null) {
                String[] words = line.split("\\,|\\.|[\\s]|\\–");
                for (String word : words) {
                    // I以外の単語を小文字にする
                    if (!word.equals("I")) {
                        word = word.toLowerCase();
                    }
                    //集計したファイルの単語数をカウント
                    if (!word.isEmpty()) {
                        if (map.containsKey(word)) {
                            int count = map.get(word) + 1;
                            map.put(word, count);
                        } else {
                            map.put(word, 1);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("ファイルが見つかりませんでした。");
        } catch (IOException e) {
            System.out.println("読み取りに失敗しました。");
        }
        //Listにmapを格納する
        List<String> list = new ArrayList<>();
        for (String key : map.keySet()) {
            list.add(key);
        }
        //アルファベット辞書順に並び替える
        Collections.sort(list, new java.util.Comparator<String>() {
            public int compare(String o1, String o2) {
                String list1 = o1.toLowerCase();
                String list2 = o2.toLowerCase();
                return list1.compareTo(list2);
            }
        });
        //for文で並び替えた文字列を出力する
        for (String word : list) {
            int count = map.get(word);
            System.out.println(word + "=" +count);
        }    
    }
}
// 完成までの時間: 3時間 00分