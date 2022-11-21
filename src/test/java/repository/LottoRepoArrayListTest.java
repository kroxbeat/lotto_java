package repository;

import entity.Lotto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class LottoRepoArrayListTest {


    @Test
    void lotto_test() {
        LottoRepoArrayList lottoRepo = new LottoRepoArrayList(new ArrayList<>());

        //당첨 로또 만들기
        lottoRepo.setPrizeLotto(new Lotto());

        //로또 구매
        IntStream.rangeClosed(1, 10000).forEach(i -> lottoRepo.saveOne(new Lotto()));

        //로또 비교
        lottoRepo.getAllList().forEach(lotto -> lotto.diffOtherAndThenGetGrade(lottoRepo.getPrizeLotto().get()));

        //당첨결과
        Map<Integer, List<Lotto>> result = lottoRepo.getAllList().stream().filter(lotto -> lotto.getGrade() != 0).collect(Collectors.groupingBy(Lotto::getGrade));

        //화면 표시 ( 키셋 구해서 정렬 및 화면 출력 )
        result.keySet().stream().sorted((i, j) -> j - i).forEach(key -> {
            List<Lotto> winner = result.get(key);
            System.out.println(key + "등  " + winner.size() + " 개\t" + winner);
        });
    }

}