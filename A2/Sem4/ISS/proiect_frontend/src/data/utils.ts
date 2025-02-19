export const convertDateTimeToField = (date: Date) => {
    // add the time zone to date
    date = new Date(date);
    const dateWithTimeZone = new Date(date.getTime() - date.getTimezoneOffset() * 60000);
    return dateWithTimeZone.toISOString().slice(0, 16);
}

export const showDateTime = (date: Date) => {
    return convertDateTimeToField(new Date(date)).replace('T', ' ');
}

export const showDate = (date: Date) => {
    return showDateTime(new Date(date)).slice(0, 10);
}