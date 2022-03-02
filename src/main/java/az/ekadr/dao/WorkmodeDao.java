package az.ekadr.dao;

import az.ekadr.entites.Workmode;

import java.util.List;

public interface WorkmodeDao {

    public List<Workmode> getAllWorkmode();
    public Workmode getWorkmodeById(Long workmodeId);
    public void addWorkmode(Workmode newWorkmode);
    public void updateWorkmode(Workmode newWorkmode,Long workmodeId);
    public void deleteWorkmodeById(Long workmodeId);
}
