import {forwardRef, useEffect, useState} from "react";
import {ItemState} from "./utils";

export const MenuItem = forwardRef(({children, index, isOpened, closeOthers, animating, itemStates}, ref) => {
    const [active, setActive] = useState(false);
    const style = {
        "--i": `${index}`,
    }

    const containsMouse = (event) => {
        const rect = ref.current.getBoundingClientRect();
        const x = event.clientX;
        const y = event.clientY;
        return x >= rect.left && x <= rect.right && y >= rect.top && y <= rect.bottom;
    }

    const handleMouseEnter = (event) => {
        if(isOpened() && itemStates.current[index] === ItemState.Displayed) {
            closeOthers(index);
            const style = ref.current.style;
            style.animation = "grow 1s ease-in-out forwards";
            itemStates.current[index] = ItemState.AnimationGrow;
        }
    }

    const handleMouseLeave = (event) => {
        if([ItemState.Selected, ItemState.AnimationGrow].includes(itemStates.current[index])) {
            const style = ref.current.style;
            style.animation = "shrink 1s ease-in-out forwards";
        }
    }

    const animationStart = (event) => {
        switch (event.animationName){
            case "popout": // meniul incepe sa se deschida
                itemStates.current[index] = ItemState.AnimationOpen
                break;
            case "popin": // meniul incepe sa se inchida
                itemStates.current[index] = ItemState.AnimationClose
                break;
            case "grow": // itemul incepe sa creasca
                itemStates.current[index] = ItemState.AnimationGrow
                break;
            case "shrink": // itemul incepe sa descreasca
                itemStates.current[index] = ItemState.AnimationShrink
                break;
        }
    }

    const animationEnd = (event) => {
        switch (event.animationName){
            case "popout": // meniul a fost deschis
                itemStates.current[index] = ItemState.Displayed
                animating.current = false;
                setActive(false);
                break;
            case "popin": // meniul a fost inchis
                itemStates.current[index] = ItemState.NotDisplayed
                break;
            case "grow": // itemul este selectat
                itemStates.current[index] = ItemState.Selected
                break;
            case "shrink": // itemul a fost deselectat
                itemStates.current[index] = ItemState.Displayed
                break;
        }
    }

    // // https://stackoverflow.com/questions/19469881/remove-all-event-listeners-of-specific-type
    return (
        <div className="menuItem"
             style={style} ref={ref}
             onAnimationEnd={animationEnd}
             onAnimationStart={animationStart}
             onMouseEnter={handleMouseEnter}
             onMouseLeave={handleMouseLeave}
             >
            {children}
        </div>
    )
});
