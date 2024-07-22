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
    lng: 'en',
    debug: true,
    resources: resources,
    // if you see an error like: "Argument of type 'DefaultTFuncReturn' is not assignable to parameter of type xyz"
    // set returnNull to false (and also in the i18next.d.ts options)
    // returnNull: false,
});