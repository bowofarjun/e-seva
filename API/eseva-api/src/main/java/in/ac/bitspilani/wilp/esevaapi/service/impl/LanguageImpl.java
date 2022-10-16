package in.ac.bitspilani.wilp.esevaapi.service.impl;

import in.ac.bitspilani.wilp.esevaapi.model.Language;
import in.ac.bitspilani.wilp.esevaapi.repository.LanguageRepository;
import in.ac.bitspilani.wilp.esevaapi.service.ILanguage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageImpl implements ILanguage {

    @Autowired
    LanguageRepository languageRepository;

    @Override
    public List<Language> getAllLanguages() {
        return languageRepository.GET_ALL_LANGUAGES();
    }
}
