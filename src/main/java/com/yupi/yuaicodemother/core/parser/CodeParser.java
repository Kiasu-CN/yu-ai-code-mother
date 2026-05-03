package com.yupi.yuaicodemother.core.parser;

import com.yupi.yuaicodemother.ai.model.HtmlCodeResult;

public interface CodeParser<T> {

    /**
     * 解析代码内容
     * 
     * @param codeContent 原始代码内容
     * @return
     */
    T parseCode(String codeContent);

}
