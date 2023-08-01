package com.github.AleksandrSpencer.mtb.service;

import com.github.AleksandrSpencer.mtb.javarushclient.dto.GroupDiscussionInfo;
import com.github.AleksandrSpencer.mtb.repository.entity.GroupSub;

/**
 * Service for manipulating with {@link GroupSub}.
 */
public interface GroupSubService {

    GroupSub save(String chatId, GroupDiscussionInfo groupDiscussionInfo);
}