import {ChangeEvent} from "react";
import {useTranslation} from "react-i18next";

// eslint-disable-next-line @typescript-eslint/ban-ts-comment
// @ts-expect-error
function LanguageDropdown({isAbsolute}) {
    const {i18n} = useTranslation()

    function onLanguageChange(event: ChangeEvent<HTMLSelectElement>) {
        i18n.changeLanguage(event.target.value);
    }

    return (
        <>
            <select onChange={onLanguageChange}
                    className={(isAbsolute ? 'absolute ' : '') + 'right-10 top-8 w-64 h-12'}>
                <option value="en" selected={i18n.language == 'en'}>English</option>
                <option value="de" selected={i18n.language == 'de'}>Deutsch</option>
            </select>
        </>
    )
}

export default LanguageDropdown
