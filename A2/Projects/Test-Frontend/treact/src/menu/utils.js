import {Children, isValidElement, ReactNode} from "react";
import {MenuItem} from "./MenuItem";
import {MenuCenter} from "./MenuCenter";

function getValidChildren(children: ReactNode) {
    return Children.toArray(children).filter((child) =>
        isValidElement(child),
    )
}

function getMenuItems(children: ReactNode) {
    return getValidChildren(children).filter((child) =>
        child.type === MenuItem,
    )
}

function getMenuCenter(children: ReactNode) {
    return getValidChildren(children).filter((child) =>
        child.type === MenuCenter,
    )
}

const ItemState = {
    NotDisplayed: 0,
    AnimationOpen: 1,
    Displayed: 2,
    AnimationGrow: 3,
    Selected: 4,
    AnimationShrink: 5,
    AnimationClose: 6,
}

const isItemAnimating = (itemStateValue: number):boolean => {
    const keyStr = Object.keys(ItemState).find(key => ItemState[key] === itemStateValue).at(0);
    return keyStr.startsWith("Animation");
}

export {getMenuItems, getMenuCenter, ItemState, isItemAnimating};