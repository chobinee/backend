package grooteogi.mapper;

import grooteogi.domain.Post;
import grooteogi.domain.PostHashtag;
import grooteogi.domain.Schedule;
import grooteogi.domain.User;
import grooteogi.dto.PostDto;
import grooteogi.dto.PostDto.Request;
import grooteogi.dto.ScheduleDto;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PostMapper extends BasicMapper<PostDto, Post> {

  PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

  @Mapping(source = "dto.title", target = "title")
  @Mapping(source = "dto.content", target = "content")
  @Mapping(source = "dto.credit", target = "credit")
  @Mapping(source = "dto.imageUrl", target = "imageUrl")
  @Mapping(source = "user", target = "user")
  Post toEntity(Request dto, User user, List<PostHashtag> postHashtags);

  @Mapping(source = "post.id", target = "postId")
  PostDto.Response toResponseDto(Post post);

  @Mappings({
      @Mapping(target = "date", source = "dto.date", dateFormat = "yyyy-MM-dd"),
      @Mapping(target = "startTime", source = "dto.startTime", dateFormat = "HH:mm:ss"),
      @Mapping(target = "endTime", source = "dto.endTime", dateFormat = "HH:mm:ss")
  })
  Schedule toScheduleEntity(ScheduleDto.Request dto);

  @Mappings({
      @Mapping(target = "date", source = "dto.date", dateFormat = "yyyy-MM-dd"),
      @Mapping(target = "startTime", source = "dto.startTime", dateFormat = "HH:mm:ss"),
      @Mapping(target = "endTime", source = "dto.endTime", dateFormat = "HH:mm:ss")
  })
  List<Schedule> toScheduleEntities(List<ScheduleDto.Request> dto);


  default String asStringDate(Date date) {
    return date != null ? new SimpleDateFormat("yyyy-MM-dd")
        .format(date) : null;
  }

  default Date asDate(String date) {
    if (date == null) {
      return null;
    } else {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      String ss = sdf.format(new java.util.Date());
      return Date.valueOf(ss);
    }
  }

  default String asStringTime(Time time) {
    return time != null ? new SimpleDateFormat("HH:mm:ss")
        .format(time) : null;
  }

  default Time adTime(String time) {
    if (time == null) {
      return null;
    } else {
      SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
      String ss = sdf.format(new java.util.Date());
      return Time.valueOf(ss);
    }
  }

  @Mapping(source = "dto.title", target = "title")
  @Mapping(source = "dto.content", target = "content")
  @Mapping(source = "dto.credit", target = "credit")
  @Mapping(source = "dto.imageUrl", target = "imageUrl")
  @Mapping(source = "post.schedules", target = "schedules")
  Post toModify(Post post, PostDto.Request dto);
}
