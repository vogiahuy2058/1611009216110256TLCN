package coffeesystem.service.invoice;

import coffeesystem.dto.BranchShopStatisticsDto;

import java.util.Comparator;

public class TotalPriceComparator implements Comparator<BranchShopStatisticsDto> {
    public int compare(BranchShopStatisticsDto bss1, BranchShopStatisticsDto bss2) {
        if (bss1.getTotalPrice() == bss2.getTotalPrice())
            return 0;
        else if (bss1.getTotalPrice() > bss2.getTotalPrice())
            return -1;
        else
            return 1;
    }
}
