package com.liao27.model.dto;

import com.google.common.collect.Lists;
import com.liao27.model.entity.Category;
import com.liao27.model.entity.Game;
import com.liao27.model.entity.Index;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 首页内容
 * Created by main on 2018/5/8.
 */
@Slf4j
@Data
public class IndexBean {

    private Long id;

    /**
     * 首页第一部分的游戏列表
     */
    private List<GameBean> gameList1 = Lists.newArrayList();

    /**
     * 首页第2部分的游戏列表[精品推荐]
     */
    private List<GameBean> gameList2 = Lists.newArrayList();

    private List<GameBean> gameSubList = Lists.newArrayList();

    public void setGameList1(List<GameBean> gameList1) {
        this.gameList1 = gameList1;
        if (this.getGameList1().size() > 1){
            List<GameBean> list = Lists.newArrayList();
            for (int i = 1; i < this.gameList1.size() ; i++) {
                list.add(this.gameList1.get(i));
            }
            this.gameSubList = list;
        }else{
            this.gameSubList = Lists.newArrayList();
        }
    }

    public static IndexBean build(Index index){
        if (index == null){
            return null;
        }
        IndexBean indexBean = new IndexBean();
        BeanUtils.copyProperties(index,indexBean,"gameList1","gameList2");

        indexBean.setGameList1(GameBean.builds(index.getGameList1()));
        indexBean.setGameList2(GameBean.builds(index.getGameList2()));
        return indexBean;
    }


}
