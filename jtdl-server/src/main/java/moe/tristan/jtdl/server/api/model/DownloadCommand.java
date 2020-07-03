package moe.tristan.jtdl.server.api.model;

import static org.immutables.value.Value.Immutable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.treatwell.immutables.styles.DefaultStyle;

@Immutable
@DefaultStyle
@JsonSerialize(as = ImmutableDownloadCommand.class)
@JsonDeserialize(as = ImmutableDownloadCommand.class)
public interface DownloadCommand {

    String getSource();

    String getDestination();

}
