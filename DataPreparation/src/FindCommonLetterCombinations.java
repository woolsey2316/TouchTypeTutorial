import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.io.Serializable;


import org.apache.spark.api.java.*;
import org.apache.spark.SparkConf;

import scala.Tuple2;

/**
*submit to a yarn cluster
*
spark-submit  \
 --class FindCommonLetterCombinations \
 --master local[4] \
 --driver-memory 2g \
 --num-executors 1 \
 Subwords.jar \
 out/
*
Generates random, yet readable and pronounceable words using the phonetic rules of the english
language. trains on natural combination of words that as opposed to randomn letters. 
*/

public class FindCommonLetterCombinations {

  public static void main(String[] args) {

    SparkConf conf = new SparkConf();
    JavaSparkContext sc = new JavaSparkContext(conf);

    JavaRDD<String> dictionary = sc.textFile("words.txt")
      .map( s-> s.toLowerCase())
      .filter(s-> s.length() > 3)
      .filter(s-> !s.contains("-"));

    JavaPairRDD<String,Integer> subwordList = dictionary.flatMap(word -> {
        List<String> subword = new ArrayList<>();
        for (int subwordsize=3;subwordsize<6;++subwordsize) {
            if (word.length()>=subwordsize) {
                for (int ind = 0; ind < word.length()-subwordsize; ind++) {  
                  subword.add(word.substring(ind,subwordsize+ind));
                }
            }
        }
        return subword.iterator();
    })
        .mapToPair(word -> new Tuple2<>(word, 1))
        .reduceByKey((a, b) -> a + b);

    JavaPairRDD<Integer,String> sorted = subwordList.mapToPair(tuple -> {
        return new Tuple2<>(tuple._2,tuple._1);})
        .sortByKey(false)
        .filter(tuple -> tuple._1 >= 10)
        .filter(contains);

    sorted.map(tuple -> tuple._2)
        .saveAsTextFile("output");
    
    sc.close();
    System.exit(0);
    
  }

}