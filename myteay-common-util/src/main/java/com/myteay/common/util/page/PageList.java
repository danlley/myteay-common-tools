/*
 * Copyright (c) 2000-2004 All Rights Reserved.
 */
package com.myteay.common.util.page;

import java.util.ArrayList;
import java.util.Collection;

/**
 * ��������ҳ����Ϣ��<code>List</code>��
 *
 * @author min.weixm
 * @version $Id: PageList.java, v 0.1 Oct 29, 2017 12:34:45 PM min.weixm Exp $
 */
public class PageList<T> extends ArrayList<T> {
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 3257568390985103409L;
    private Paginator         paginator;

    /**
     * ����һ��<code>PageList</code>��
     */
    public PageList() {
        paginator = new Paginator();
    }

    /**
     * ����<code>PageList</code>������ָ��<code>Collection</code>�е����ݸ��Ƶ��µ�list�С�
     *
     * @param c Ҫ���Ƶ�<code>Collection</code>
     */
    public PageList(Collection<T> c) {
        this(c, null);
    }

    /**
     * ����<code>PageList</code>������ָ��<code>Collection</code>�е����ݸ��Ƶ��µ�list�С�
     *
     * @param c Ҫ���Ƶ�<code>Collection</code>
     */
    public PageList(Collection<T> c, Paginator paginator) {
        super(c);
        this.paginator = (paginator == null) ? new Paginator() : paginator;
    }

    /**
     * ȡ�÷�ҳ�����ɴ���ȡ���йط�ҳ��ҳ���������Ϣ��
     *
     * @return ��ҳ������
     */
    public Paginator getPaginator() {
        return paginator;
    }

    /**
     * ���÷�ҳ����
     *
     * @param paginator Ҫ���õķ�ҳ������
     */
    public void setPaginator(Paginator paginator) {
        if (paginator != null) {
            this.paginator = paginator;
        }
    }

}
