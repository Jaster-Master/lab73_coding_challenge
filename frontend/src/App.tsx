import './App.css'
import Login from "./components/Login.tsx";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Home from "./components/Home.tsx";

function App() {
    return (
        <>
            <BrowserRouter>
                <Routes>
                    <Route path="/">
                        <Route index element={<Login/>}/>
                        <Route path="home" element={<Home/>}/>
                    </Route>
                </Routes>
            </BrowserRouter>
        </>
    )
}

export default App
