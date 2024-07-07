import {API} from "./static";
import {redirect} from "react-router-dom";

var _csrfToken = getCsrfToken();

export async function getCsrfToken():Promise<string> {

    const response = await fetch(`${API}/csrf/`, {
        credentials: 'include',
    });
    const data = await response.json();
    _csrfToken = data.csrfToken;
    return _csrfToken;
}

export function getCookie(name: string) : string {
    let cookieValue = "";
    if (document.cookie && document.cookie !== '') {
        const cookies = document.cookie.split(';');
        for (let i = 0; i < cookies.length; i++) {
            const cookie = cookies[i].trim();
            // Does this cookie string begin with the name we want?
            if (cookie.substring(0, name.length + 1) === (name + '=')) {
                cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
                break;
            }
        }
    }
    return cookieValue;
}

var csrftoken:string = getCookie('csrftoken')

export const CSRFToken = () => {
    return (
        <input type="hidden" name="csrfmiddlewaretoken" value={csrftoken}/>
    )
}

export const handleLogout = () => {
    return fetch(API+'/logout/', {
        credentials: 'include',
        method: 'POST',
        mode: 'cors',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-CSRFToken': getCookie('csrftoken'),
        },
    }).then((response) => {
        if (response.ok) {
            window.location.href = "/";
        } else {
            console.warn("Logout failed");
        }
    });
}