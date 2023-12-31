package org.intensio.controller.command.Impl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.intensio.beans.Attributes;
import org.intensio.beans.Movie;
import org.intensio.controller.command.Command;
import org.intensio.service.ReviewService;
import org.intensio.service.ServiceFactory;
import org.intensio.service.exception.ServiceException;
import java.util.Optional;

public class MovieCommand implements Command {

    ReviewService reviewService = ServiceFactory.getInstance().getReviewService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException{
        if(request.getParameter("id") == null && request.getSession().getAttribute("movie") != null){
            return "WEB-INF/view/movie.jsp";
        }
        try{
            int id = Integer.parseInt(request.getParameter("id"));
            Optional<Movie> movie = ServiceFactory.getInstance().getMovieService().getMovieById(id);

            if(movie.isEmpty()) {
                throw new ServletException("Unknown movie");
            }

            movie.get().setReviewList(reviewService.getMoviesReviews(movie.get()));
            request.getSession().setAttribute(Attributes.MOVIE, movie.get());
        }
        catch (NumberFormatException e){
            throw new ServletException(e.getMessage());
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }

        return "WEB-INF/view/movie.jsp";
    }
}
