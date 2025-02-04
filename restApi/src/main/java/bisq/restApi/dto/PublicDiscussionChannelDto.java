package bisq.restApi.dto;

import bisq.chat.discuss.pub.PublicDiscussionChannel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;


@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@ToString
@Schema(name = "PublicDiscussionChannel")
public final class PublicDiscussionChannelDto {
    @EqualsAndHashCode.Include
    private String id;
    private String channelName;
    private String description;
    private String channelAdminId;
    private List<String> channelModeratorIds;

    public static PublicDiscussionChannelDto from(PublicDiscussionChannel publicDiscussionChannel) {
        PublicDiscussionChannelDto dto = new PublicDiscussionChannelDto();
        dto.id = publicDiscussionChannel.getId();
        dto.channelName = publicDiscussionChannel.getChannelName();
        dto.description = publicDiscussionChannel.getDescription();
        dto.channelAdminId = publicDiscussionChannel.getChannelAdminId();
        dto.channelModeratorIds = publicDiscussionChannel.getChannelModeratorIds();
        return dto;
    }
}
