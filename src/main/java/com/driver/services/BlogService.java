package com.driver.services;

import com.driver.models.Blog;
import com.driver.models.Image;
import com.driver.models.User;
import com.driver.repositories.BlogRepository;
import com.driver.repositories.ImageRepository;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository1;

    @Autowired
    ImageService imageService1;

    @Autowired
    UserRepository userRepository1;

    public List<Blog> showBlogs(){
        //find all blogs
        List<Blog> blogList = blogRepository1.findAll();
        return blogList;
    }

    public void createAndReturnBlog(Integer userId, String title, String content) {
        //create a blog at the current time

        //updating the blog details

        //Updating the userInformation and changing its blogs
        User user = userRepository1.findByUserId(userId);
        Blog blog = new Blog();
        blog.setId(blog.getId());
        blog.setUser(user);
        blog.setTitle(title);
        blog.setContent(content);
        List<Blog> blogList = user.getBlogList();
        blogList.add(blog);
        userRepository1.save(user);
        blogRepository1.save(blog);
    }

//    public Blog findBlogById(int blogId){
//        //find a blog
//       return null;
//    }

    public void addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog after creating it
        Blog blog = blogRepository1.findBlogById(blogId);
        List<Image> imageList = blog.getImageList();
        Image image = new Image();
        image.setDescription(description);
        image.setDimensions(dimensions);
        imageList.add(image);
        blog.setImageList(imageList);
        blogRepository1.save(blog);
    }

    public void deleteBlog(int blogId){
        //delete blog and corresponding images
        Blog blog = blogRepository1.findBlogById(blogId);
        List<Image> imageList = blog.getImageList();
        imageList = null;
        blog.setImageList(imageList);
        blogRepository1.deleteById(blogId);
    }
}
