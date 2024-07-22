import Login from "./components/Login.tsx";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Home from "./components/Home.tsx";
import './i18n/config.ts'

function App() {
    return (
        <>
            <div className="m-6 text-left min-h-full">
                <BrowserRouter>
                    <Routes>
                        <Route path="/">
                            <Route index element={<Login/>}/>
                            <Route path="home" element={<Home/>}/>
                        </Route>
                    </Routes>
                </BrowserRouter>
            </div>
        </>
    )
}

export default App
