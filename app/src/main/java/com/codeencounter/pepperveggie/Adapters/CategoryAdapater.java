package com.codeencounter.pepperveggie.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codeencounter.pepperveggie.CategoryActivity;
import com.codeencounter.pepperveggie.R;

import java.util.List;

public class CategoryAdapater extends RecyclerView.Adapter<CategoryAdapater.ViewHolder> {

    private List<CategoryModel> categoryModelList;

    public CategoryAdapater(List<CategoryModel> categoryModelList) {
        this.categoryModelList = categoryModelList;
    }

    @NonNull
    @Override
    public CategoryAdapater.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapater.ViewHolder holder, int position) {
        String icon = categoryModelList.get(position).getCatergoryIconLink();
        String name = categoryModelList.get(position).getCategoryName();
        holder.setCategory(name,position);
    }

    @Override
    public int getItemCount() {
        return categoryModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView categoryIcon;
        private TextView categoryName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryIcon = itemView.findViewById(R.id.category_icon);
            categoryName = itemView.findViewById(R.id.category_name);

        }

        private void setCategoryIcon(){
            //set category icons here
        }

        private void setCategory(final String name, final int position){
            //set category names here
            categoryName.setText(name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(position != 0){
                        Intent categoryIntent = new Intent(itemView.getContext(), CategoryActivity.class);
                        categoryIntent.putExtra("CategoryName",name);
                        itemView.getContext().startActivity(categoryIntent);
                    }

                }
            });

        }


    }
}
