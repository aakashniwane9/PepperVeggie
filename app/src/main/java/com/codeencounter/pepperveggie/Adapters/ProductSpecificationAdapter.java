package com.codeencounter.pepperveggie.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codeencounter.pepperveggie.R;

import org.w3c.dom.Text;

import java.util.List;

public class ProductSpecificationAdapter extends RecyclerView.Adapter<ProductSpecificationAdapter.ViewHolder> {

    public List<ProductSpecificationModel> productSpecificationModelList;

    public ProductSpecificationAdapter(List<ProductSpecificationModel> productSpecificationModelList) {
        this.productSpecificationModelList = productSpecificationModelList;
    }

    public int getItemViewType(int position){
        switch(productSpecificationModelList.get(position).getType()){
            case 0:
                return ProductSpecificationModel.SPECIFICATION_TITILE;
            case 1:
                return ProductSpecificationModel.SPECIFICATION_BODY;
            default:
                return -1;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (viewType)
        {
            case ProductSpecificationModel.SPECIFICATION_TITILE:
                TextView title =new TextView(parent.getContext());
                title.setTypeface(null, Typeface.BOLD);
                title.setTextColor(Color.rgb(255,255,255));
                LinearLayout.LayoutParams layoutParams= new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(setDp(16,parent.getContext()),
                        setDp(16,parent.getContext()),
                        setDp(16,parent.getContext()),
                        setDp(8, parent.getContext()));

                title.setLayoutParams(layoutParams);
                return new ViewHolder(title);

            case ProductSpecificationModel.SPECIFICATION_BODY:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_specification_item_layout,parent,false);
                return new ViewHolder(view);

            default:
                return null;
        }




    }

    @Override
    public void onBindViewHolder(@NonNull ProductSpecificationAdapter.ViewHolder holder, int position) {

        switch (productSpecificationModelList.get(position).getType())
        {
            case ProductSpecificationModel.SPECIFICATION_TITILE:
                holder.setTitle(productSpecificationModelList.get(position).getTitle());
                break;
            case ProductSpecificationModel.SPECIFICATION_BODY:
                String featureTitle = productSpecificationModelList.get(position).getFeatureName();
                String featureDetail = productSpecificationModelList.get(position).getFeatureValue();
                holder.setFeatures(featureTitle,featureDetail);
                break;


            default:return ;

        }

       }

    @Override
    public int getItemCount() {
        return productSpecificationModelList.size();
    }

    private int setDp(int dp, Context context)
    {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,context.getResources().getDisplayMetrics());
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView featureName,featureValue;
        private TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

          }

          private void setTitle(String titleText)
          {
              title= (TextView) itemView;
              title.setText(titleText);
          }

        private void setFeatures(String featureTitle,String featureDetail){
            featureName = itemView.findViewById(R.id.feature_name);
            featureValue = itemView.findViewById(R.id.feature_value);

            featureName.setText(featureTitle);
            featureName.setText(featureDetail);

        }
    }
}
