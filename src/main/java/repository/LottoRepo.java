package repository;

import java.util.List;
import java.util.Optional;

public interface LottoRepo<T> {

    //저장소 모든 로또 가져오기
    List<T> getAllList();

    //저장소 사이즈 구하기
    int getTotalSize();

    //저장소에 단일 로또 저장
    void saveOne(T t);

    //당첨 로또 가져오기
    Optional<T> getPrizeLotto();

    //당첨 로또 새로넣기
    void setPrizeLotto(T t);
}
