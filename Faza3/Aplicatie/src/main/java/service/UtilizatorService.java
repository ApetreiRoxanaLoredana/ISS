package service;

import domain.Utilizator;
import repository.UtilizatorRepository;

public class UtilizatorService {
    private UtilizatorRepository utilizatorRepository;

    public UtilizatorService(UtilizatorRepository utilizatorRepository) {
        this.utilizatorRepository = utilizatorRepository;
    }

    public Utilizator cautaUtilizatorDupaUsernameSiParola(String username, String parola) throws ServiceException {
        Utilizator utilizator = utilizatorRepository.cautaDupaUsernameSiParola(username, parola);
        if (utilizator == null)
            throw new ServiceException("Nu exista nici un utilizator cu aceste date");
        return utilizator;
    }
}
