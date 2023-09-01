var handleLoader, handleNativeBackCallInit, handleLocPerDenied, handleCloseMyPage, handleLocation, handleSocialData, handleAuthCode, handleEventDetailBtnClick, goOutLink, digitalData = {};
function setHandleLoader(e) {
    handleLoader = e
}
function setHandleNativeBackCallInit(e) {
    handleNativeBackCallInit = e
}
function setHandleLocPerDenied(e) {
    handleLocPerDenied = e
}
function setHandleCloseMyPage(e) {
    handleCloseMyPage = e
}
function setHandleLocation(e) {
    handleLocation = e
}
function setHandleSocialData(e) {
    handleSocialData = e
}
function setHandleAuthCode(e) {
    handleAuthCode = e
}
function setEventDetailBtnClick(e) {
    handleEventDetailBtnClick = e
}
function hideLoader() {
    handleLoader && handleLoader(!1)
}
function nativeBackCallInit() {
    handleNativeBackCallInit && handleNativeBackCallInit()
}
function locPerDenied() {
    handleLocPerDenied && handleLocPerDenied()
}
function closeMyPage() {
    handleCloseMyPage && handleCloseMyPage()
}
function setLocation(e, n) {
    handleLocation && handleLocation(e, n)
}
function resSiginData(e, n, a, t, l, i, o) {
    handleSocialData && handleSocialData({
        userType: e,
        socialType: n,
        userId: a,
        userEmail: t,
        ci: l,
        accessToken: i,
        refreshToken: o
    })
}
function resAuthorizationCode(e) {
    handleAuthCode && handleAuthCode({
        authorizationCode: e
    })
}
function eventDetailBtnClick(e) {
    handleEventDetailBtnClick && handleEventDetailBtnClick(e)
}
function nativeBackBtnCallback() {
    return "N"
}
