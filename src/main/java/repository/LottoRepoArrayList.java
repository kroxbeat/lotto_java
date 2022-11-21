package repository;

import entity.Lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LottoRepoArrayList implements LottoRepo<Lotto>{

    private final ArrayList<Lotto> repo;

    private Optional<Lotto> prize;

    public LottoRepoArrayList(ArrayList<Lotto> repo) {
        this.repo = repo;
        this.prize = Optional.ofNullable(null);
    }

    @Override
    public List<Lotto> getAllList() {
        return this.repo;
    }

    @Override
    public int getTotalSize() {
        return this.repo.size();
    }

    @Override
    public void saveOne(Lotto lotto) {
        this.repo.add(lotto);
    }

    @Override
    public Optional<Lotto> getPrizeLotto() {
        return this.prize;
    }

    @Override
    public void setPrizeLotto(Lotto lotto) {
        this.prize = Optional.ofNullable(lotto);
    }


}
