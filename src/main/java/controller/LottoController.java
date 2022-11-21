package controller;

import exception.LottoInputException;
import service.LottoService;

public class LottoController {
    private final LottoService service;

    public LottoController(LottoService service) {
        this.service = service;
    }

    public void start() {
        System.out.println("환영 합니다.");
        while (true) {
            String command = service.getInputMsg();
            control(command);
        }
    }

    private void control(String next) {
        if (next == null || next.trim().length() == 0 )
            return;
        switch (next) {
            case "exit":
                System.exit(0);
                break;
            case "help":
                service.printMenu();
                break;
            case "status":
                service.status();
                break;
            case "buy":
                service.buy();
                break;
            case "gogo":
                try {
                    service.gogo();
                } catch (LottoInputException e) {
                    System.out.println("유효하지 않은 입력 입니다.");
                }
                break;
            case "result":
                service.result();
                break;

        }
    }    
}
