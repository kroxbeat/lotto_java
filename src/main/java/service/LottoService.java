package service;

import entity.Lotto;
import exception.LottoInputException;
import repository.LottoRepo;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoService {

    private final Scanner scan;
    private final LottoRepo<Lotto> repo;

    public LottoService(Scanner scan, LottoRepo<Lotto> repo) {
        this.scan = scan;
        this.repo = repo;
    }

    public String getInputMsg() {
        System.out.print("> ");
        return scan.nextLine().trim();
    }

    public String getInputMsg(String msg) {
        System.out.print(msg + " >");
        return scan.nextLine().trim();
    }

    public void printMenu() {
        System.out.println("메뉴 입력 ex) status, buy, remove, gogo , result");
    }

    public void status() {
        int totalSize = this.repo.getTotalSize();
        System.out.println("총 구매 개수 : "+totalSize);

        Optional<Lotto> prizeLotto = this.repo.getPrizeLotto();
        //존재여부에 따라
        if (prizeLotto.isPresent()) {
            System.out.println("당첨 번호 : "+prizeLotto.get().getNum6());
        }else {
            System.out.println("추첨 전 입니다.");
        }
    }

    public void buy() {
        String buyCount = getInputMsg("구매 개수 입력 >");
        //문자 -> 숫자 변환
        int i = Integer.parseInt(buyCount);
        IntStream.rangeClosed(1,i)
                .forEach(noUse -> this.repo.saveOne(new Lotto()));
    }

    public void gogo() {
        String inputMsg = getInputMsg("자동으로 구성 하시겠습니까?  press Enter or n");
        //자동 구성 수동 구성 분기
        if ("n".equals(inputMsg)){
            String inputNums = getInputMsg("숫자 6개 를 입력해 주세요 ex) 11,12,13,14,15,16");
            String[] split = inputNums.split(",");
            if (split.length != 6)
                throw new LottoInputException();
            Set<Integer> inputCollectSet = Arrays.stream(split).map(value -> Integer.parseInt(value)).collect(Collectors.toSet());
            //입력 숫자 개수 여부에 따라 성공 분기
            if (inputCollectSet.size() != 6)
                throw new LottoInputException();
                this.repo.setPrizeLotto(new Lotto(inputCollectSet));
        }else{
            this.repo.setPrizeLotto(new Lotto());
            System.out.println("로또 당첨 번호  "+this.repo.getPrizeLotto().get().getNum6());
        }
    }

    public void result() {
        //저장소 모든 로또 당첨번호 와 비교하기
        if (this.repo.getPrizeLotto().isPresent())
        this.repo.getAllList()
                .forEach(lotto -> lotto.diffOtherAndThenGetGrade(this.repo.getPrizeLotto().get()));

        //등수 별 분리
        Map<Integer, List<Lotto>> result = this.repo.getAllList()
                .stream().filter(lotto -> lotto.getGrade() != 0)
                .collect(Collectors.groupingBy(Lotto::getGrade));

        //통계
        result.keySet().stream()
                .sorted((i,j) -> j-i)
                .forEach(key -> {
                    List<Lotto> winner = result.get(key);
                    System.out.println(key + "등  " + winner.size() + " 개\t" + winner);
                });
    }
}
