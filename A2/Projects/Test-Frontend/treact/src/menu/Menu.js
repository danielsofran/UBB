import {cloneElement, createRef, useEffect, useRef, useState} from "react";
import {getMenuCenter, getMenuItems, isItemAnimating, ItemState} from './utils'

export function Menu({ children }) {
    // get number of valid children
    const centerItem = getMenuCenter(children)[0];
    const menuItems = getMenuItems(children);
    const menuItemsRef = useRef([]);

    const [opened, setOpened] = useState(false);
    const isOpened = ():boolean => opened;

    const menuAnimating = useRef(false);
    const itemStates = useRef([]);

    const closeAllItemsExceptIndex = (index: number) => {
        const canClose = (i: number):boolean => {
            const state = itemStates.current.at(i)
            const states = [ItemState.Selected, ItemState.AnimationGrow, ItemState.AnimationShrink]
            return states.includes(state)
        }
        menuItemsRef.current.forEach((ref, i) => {
            if(ref.current && i !== index && canClose(i)){
                const style = ref.current.style;
                style.animation = "shrink 1s ease-in-out forwards";
                itemStates.current[i] = ItemState.AnimationShrink;
            }
        })
    }

    useEffect(() => {
        menuItemsRef.current = Array.from({length: menuItems.length+1}, (_, i) => createRef())
        itemStates.current = Array.from({length: menuItems.length+1}, (_, i) => ItemState.NotDisplayed)
    }, [])

    useEffect(() => {
        if(opened) { // menu has been opened
            console.warn('Menu: opened')
            // loop menuItemsRef
            menuItemsRef.current.forEach((ref, i) => {
                if(ref.current) {
                    const style = ref.current.style;
                    //style.display = 'block'; // sa dispara cand inchid meniul?
                    style.animation = "popout 2s ease-out forwards";
                    itemStates.current[i] = ItemState.AnimationOpen;
                }
            })
        }
        else{
            console.warn('Menu: closed')
            setOpened(false)
            menuItemsRef.current.forEach((ref, i) => {
                if(ref.current) {
                    const style = ref.current.style;
                    style.animation = "popin 2s ease-in-out forwards";
                    itemStates.current[i] = ItemState.AnimationClose;
                }
            })

        }
    }, [opened]);

    const style = {
        "--m": `${menuItems.length}`,
        "--tan": 0.4142135623730951,
    };

    const childrenWithProps = () => {
        let index = 1;
        return menuItems.map((child) => {
            return cloneElement(child, {
                ref: menuItemsRef.current[index],
                isOpened: isOpened,
                closeOthers: closeAllItemsExceptIndex,
                animating: menuAnimating,
                index: index++,
                itemStates: itemStates,
            });
        });
    }

    return (
        <div className="menuContainer" style={style}>
            {cloneElement(centerItem, {
                onClick: () => {
                    setOpened(!opened)
                    menuAnimating.current = true;
                    console.warn('CentralItem: opened='+isOpened())
                },
            })}
            {childrenWithProps()}
        </div>
    )
}
