package com.github.AleksandrSpencer.mtb.service;

import com.github.AleksandrSpencer.mtb.javarushclient.JavaRushGroupClient;
import com.github.AleksandrSpencer.mtb.javarushclient.dto.GroupDiscussionInfo;
import com.github.AleksandrSpencer.mtb.repository.GroupSubRepository;
import com.github.AleksandrSpencer.mtb.repository.entity.GroupSub;
import com.github.AleksandrSpencer.mtb.repository.entity.TelegramUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

@DisplayName("Unit-level testing for GroupSubService")
public class GroupSubServiceTest {

    private GroupSubService groupSubService;
    private GroupSubRepository groupSubRepository;
    private JavaRushGroupClient javaRushGroupClient;
    private TelegramUser newUser;

    private final static String CHAT_ID = "1234234";
    private final static Integer GROUP_ID = 1123;
    private final static Integer LAST_ARTICLE_ID = 310;

    @BeforeEach
    public void init() {
        TelegramUserService telegramUserService = Mockito.mock(TelegramUserService.class);
        groupSubRepository = Mockito.mock(GroupSubRepository.class);
        javaRushGroupClient = Mockito.mock(JavaRushGroupClient.class);
        groupSubService = new GroupSubServiceImpl(groupSubRepository, telegramUserService, javaRushGroupClient);

        newUser = new TelegramUser();
        newUser.setActive(true);
        newUser.setChatId(CHAT_ID);

        Mockito.when(telegramUserService.findByChatId(CHAT_ID)).thenReturn(Optional.of(newUser));
        Mockito.when(javaRushGroupClient.findLastArticleId(GROUP_ID)).thenReturn(LAST_ARTICLE_ID);
    }

    @Test
    public void shouldProperlySaveGroup() {
        //given

        GroupDiscussionInfo groupDiscussionInfo = new GroupDiscussionInfo();
        groupDiscussionInfo.setId(GROUP_ID);
        groupDiscussionInfo.setTitle("g1");

        GroupSub expectedGroupSub = new GroupSub();
        expectedGroupSub.setId(groupDiscussionInfo.getId());
        expectedGroupSub.setTitle(groupDiscussionInfo.getTitle());
        expectedGroupSub.setLastArticleId(LAST_ARTICLE_ID);
        expectedGroupSub.addUser(newUser);

        //when
        groupSubService.save(CHAT_ID, groupDiscussionInfo);

        //then
        Mockito.verify(groupSubRepository).save(expectedGroupSub);
    }

    @Test
    public void shouldProperlyAddUserToExistingGroup() {
        //given
        TelegramUser oldTelegramUser = new TelegramUser();
        oldTelegramUser.setChatId("2");
        oldTelegramUser.setActive(true);

        GroupDiscussionInfo groupDiscussionInfo = new GroupDiscussionInfo();
        groupDiscussionInfo.setId(1);
        groupDiscussionInfo.setTitle("g1");

        GroupSub groupFromDB = new GroupSub();
        groupFromDB.setId(groupDiscussionInfo.getId());
        groupFromDB.setTitle(groupDiscussionInfo.getTitle());
        groupFromDB.addUser(oldTelegramUser);

        Mockito.when(groupSubRepository.findById(groupDiscussionInfo.getId())).thenReturn(Optional.of(groupFromDB));

        GroupSub expectedGroupSub = new GroupSub();
        expectedGroupSub.setId(groupDiscussionInfo.getId());
        expectedGroupSub.setTitle(groupDiscussionInfo.getTitle());
        expectedGroupSub.addUser(oldTelegramUser);
        expectedGroupSub.addUser(newUser);

        //when
        groupSubService.save(CHAT_ID, groupDiscussionInfo);

        //then
        Mockito.verify(groupSubRepository).findById(groupDiscussionInfo.getId());
        Mockito.verify(groupSubRepository).save(expectedGroupSub);
    }

}