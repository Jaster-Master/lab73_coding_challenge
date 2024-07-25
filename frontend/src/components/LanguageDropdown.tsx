import {ChangeEvent} from "react";
import {useTranslation} from "react-i18next";

// eslint-disable-next-line @typescript-eslint/ban-ts-comment
// @ts-expect-error
function LanguageDropdown({isAbsolute}) {
    const {i18n} = useTranslation()

    function onLanguageChange(event: ChangeEvent<HTMLSelectElement>) {
        const selectedLanguage = event.target.value;
        i18n.changeLanguage(selectedLanguage).then(() => {
            localStorage.setItem('lang', selectedLanguage);
        });
    }

    return (
        <>
            <select defaultValue={i18n.language} onChange={onLanguageChange}
                    className={(isAbsolute ? 'absolute ' : '') + 'right-10 top-8 w-64 h-12'}>
                <option value="en">English</option>
                <option value="de">Deutsch</option>
            </select>
        </>
    )
}

export default LanguageDropdown
