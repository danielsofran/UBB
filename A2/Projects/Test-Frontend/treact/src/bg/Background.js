
import {useEffect} from "react";

function Background({ children }) {
    const box_clicked = () => {
        const iframe=document.getElementById('webgl_iframe');
        iframe.focus();
        console.warn("focused")
        //iframe.addEventListener('keydown', (e) => {alert("Pressed "+e.key)})
        //for(let i=0;i<1000;++i)
        //    iframe.dispatchEvent(new KeyboardEvent('keydown', {'key': 'a'}));
    }

    useEffect(() => {
        const dp = document.getElementById('bgContainer')
        const iframe = document.getElementById('webgl_iframe')
        const box = document.getElementById('boxOverBg')

        window.addEventListener("blur", () => {
            setTimeout(() => {
                if (document.activeElement.id === "webgl_iframe") {
                    console.warn("in blur")
                    setTimeout( ()=>{box.style.display = "block"}, 3000);
                }
            });
        }, { once: true });
    })

    return (
        <div id="bgContainer">
            <iframe
                title="3D View"
                tag="IFRAME"
                id='webgl_iframe'
                frameBorder="0"
                allow="autoplay; fullscreen; vr"
                allowFullScreen={true}
                allowvr="true"
                mozallowfullscreen="true"
                src="https://play.unity.com/webgl/1846beff-afea-44cf-9a7f-b50b87b80e8a"
                onmousewheel="" webkitallowfullscreen="true"
            ></iframe>
            <div id="boxOverBg"
                 onClick={box_clicked}>
                {children}
            </div>
        </div>
    )
}

export { Background }
