import {useEffect, useState} from "react";

export function MenuCenter({children, index, onClick}) {
    const style = {"--i": `${index}`}


    return (
        <div className="menuCenter" style={style} onClick={onClick}>
            {children}
        </div>
    )
}
