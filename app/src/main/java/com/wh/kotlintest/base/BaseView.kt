package com.wh.kotlintest.base

interface BaseView {

    /**显示 */
    fun showProgress()

    /**隐藏 */
    fun hideProgress()

    /**失败 */
    fun showMessage(message: String)


}
