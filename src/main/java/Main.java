import controller.LottoController;
import repository.LottoRepo;
import repository.LottoRepoArrayList;
import service.LottoService;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //스캐너
        Scanner scan = new Scanner(System.in);
        //저장소
        LottoRepo lottoRepo = new LottoRepoArrayList(new ArrayList<>());
        //서비스 생성
        LottoService lottoService = new LottoService(scan, lottoRepo);

        //컨트롤러 생성 (프로그램 시작)
        new LottoController(lottoService).start();
    }
}
