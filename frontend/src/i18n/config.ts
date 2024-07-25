import i18next from 'i18next';
import {initReactI18next} from 'react-i18next';
import enTranslation from './en.json';
import deTranslation from './de.json';

const resources = {
    en: {
        translation: enTranslation,
    },
    de: {
        translation: deTranslation,
    },
}

i18next.use(initReactI18next).init({
    fallbackLng: () => {
        const selectedLanguage = localStorage.getItem('lang');
        if (selectedLanguage != null) {
            return selectedLanguage;
        }
        return 'en';
    },
    debug: true,
    resources: resources,
});