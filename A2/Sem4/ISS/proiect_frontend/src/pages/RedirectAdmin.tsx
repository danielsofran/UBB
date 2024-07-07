import {useEffect} from "react";
import {useNavigate} from "react-router-dom";

export const RedirectAdmin = () => {
    const navigate = useNavigate();
    useEffect(() => {
        window.location.href = "http://localhost:8000/admin";
    }, []);

    return(
        <div>
        </div>
    )
}