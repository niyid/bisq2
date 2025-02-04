/*
 * This file is part of Bisq.
 *
 * Bisq is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at
 * your option) any later version.
 *
 * Bisq is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Bisq. If not, see <http://www.gnu.org/licenses/>.
 */

package bisq.chat.support.pub;

import bisq.chat.message.ChatMessage;
import bisq.chat.message.PublicChatMessage;
import bisq.chat.message.Quotation;
import bisq.common.util.StringUtils;
import bisq.network.p2p.services.data.storage.MetaData;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Optional;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public final class PublicSupportChatMessage extends PublicChatMessage {
    public PublicSupportChatMessage(String channelId,
                                    String authorId,
                                    String text,
                                    Optional<Quotation> quotedMessage,
                                    long date,
                                    boolean wasEdited) {
        this(StringUtils.createShortUid(),
                channelId,
                authorId,
                Optional.of(text),
                quotedMessage,
                date,
                wasEdited,
                new MetaData(ChatMessage.TTL, 100000, PublicSupportChatMessage.class.getSimpleName()));
    }

    private PublicSupportChatMessage(String messageId,
                                     String channelId,
                                     String authorId,
                                     Optional<String> text,
                                     Optional<Quotation> quotedMessage,
                                     long date,
                                     boolean wasEdited,
                                     MetaData metaData) {
        super(messageId,
                channelId,
                authorId,
                text,
                quotedMessage,
                date,
                wasEdited,
                metaData);
    }

    public bisq.chat.protobuf.ChatMessage toProto() {
        return getChatMessageBuilder().setPublicSupportChatMessage(bisq.chat.protobuf.PublicSupportChatMessage.newBuilder()).build();
    }

    public static PublicSupportChatMessage fromProto(bisq.chat.protobuf.ChatMessage baseProto) {
        Optional<Quotation> quotedMessage = baseProto.hasQuotation() ?
                Optional.of(Quotation.fromProto(baseProto.getQuotation())) :
                Optional.empty();
        return new PublicSupportChatMessage(
                baseProto.getMessageId(),
                baseProto.getChannelId(),
                baseProto.getAuthorId(),
                Optional.of(baseProto.getText()),
                quotedMessage,
                baseProto.getDate(),
                baseProto.getWasEdited(),
                MetaData.fromProto(baseProto.getMetaData()));
    }
}