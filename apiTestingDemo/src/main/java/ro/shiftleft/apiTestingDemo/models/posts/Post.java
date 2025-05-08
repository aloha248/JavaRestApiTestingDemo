package ro.shiftleft.apiTestingDemo.models.posts;

import lombok.*;
import ro.shiftleft.apiTestingDemo.models.base.JsonModel;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Post implements JsonModel {
    private int id;
    private String title;
    private String body;
    private int userId;
}